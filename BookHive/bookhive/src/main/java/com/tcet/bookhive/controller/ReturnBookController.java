package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ReturnBookController implements Initializable {

    @FXML
    private Label messageLabel;
    
    @FXML
    private DatePicker actualretdatepicker;

    @FXML
    private ComboBox<String> issuedbooklistcombobox;

    @FXML
    private Button retbookbtn;

    @FXML
    private HBox retbookhbox;
    
    @FXML
    private ComboBox<String> studentlistcombobox;

    @FXML
    void handlereturnbook(ActionEvent event) {
        String bookname=issuedbooklistcombobox.getSelectionModel().getSelectedItem();
        Integer student_id=Integer.parseInt(studentlistcombobox.getSelectionModel().getSelectedItem());
        LocalDate actualretdate=actualretdatepicker.getValue();
        if (bookname == null) {
            messageLabel.setText("Please select a book.");
            return;
        }if (student_id == null) {
            messageLabel.setText("Please select student ID.");
            return;
        }
        if (actualretdate == null) {
            messageLabel.setText("Please select return date.");
            return;
        }
        
        String query1="select book_id from books where title=?";
        String query2="select issue_id from issue where book_id=? and return_status='not returned' and student_id=?";
        String query3="update issue set actual_return_date=?, return_status='returned' where issue_id=?";
        String query4="update books set copies=copies+1,status='available' where book_id=?";
        String query5="update overdue set fine_status='paid' where issue_id=?";
                
        try(Connection con=DBConnection.connect();
            PreparedStatement stmt1=con.prepareStatement(query1);
            PreparedStatement stmt2=con.prepareStatement(query2);
            PreparedStatement stmt3=con.prepareStatement(query3);
            PreparedStatement stmt4=con.prepareStatement(query4);
            PreparedStatement stmt5=con.prepareStatement(query5);){
            
            int book_id=0;
            stmt1.setString(1,bookname);
            ResultSet rs1=stmt1.executeQuery();
            while(rs1.next()){
                book_id=rs1.getInt("book_id");
            }
            
            int issue_id=0;
            stmt2.setInt(1, book_id);
            stmt2.setInt(2,student_id);
            ResultSet rs2=stmt2.executeQuery();
            while(rs2.next()){
                issue_id=rs2.getInt("issue_id");
            }
            
            
            String query="select return_date from issue where issue_id=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, issue_id);
            ResultSet rs3 = stmt.executeQuery();
            LocalDate return_date = null;
            if(rs3.next()){
                return_date = rs3.getObject("return_date", LocalDate.class);
            }
            System.out.println(actualretdate+" "+return_date);
            
            if(actualretdate.isAfter(return_date)){
                long fine=5*ChronoUnit.DAYS.between(return_date, actualretdate);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Overdue Fine");
                alert.setHeaderText("This book is overdue.");
                alert.setContentText("Fine ₹" + fine + " has to be paid. Has it been paid?");
                Optional<ButtonType> result = alert.showAndWait();
                
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Fine paid — update all tables
                    //updating issue table
                    stmt3.setDate(1, java.sql.Date.valueOf(actualretdate));
                    stmt3.setInt(2, issue_id);   
                    int rowsaffected1=stmt3.executeUpdate();

                    //updating books table
                    stmt4.setInt(1, book_id);
                    int rowsaffected2=stmt4.executeUpdate();
                    
                    //updating overdue table
                    stmt5.setInt(1,issue_id);
                    int rowsaffected3=stmt5.executeUpdate();
                    
                    if(rowsaffected1>0 && rowsaffected2>0 && rowsaffected3>0){
                        messageLabel.setText("Book returned Successfully");
                    }else{
                        messageLabel.setText("Book return Failed");
                    }
                } else {
                    messageLabel.setText("Please pay the fine before returning.");
                }
            }else{
                stmt3.setDate(1, java.sql.Date.valueOf(actualretdate));
                stmt3.setInt(2, issue_id);   
                int rowsaffected1=stmt3.executeUpdate();

                stmt4.setInt(1, book_id);
                int rowsaffected2=stmt4.executeUpdate();
                if(rowsaffected1>0 && rowsaffected2>0){
                    messageLabel.setText("Book returned Successfully");
                }else{
                    messageLabel.setText("Book return Failed");
                }
            }
                       
        }catch(Exception e){
            e.printStackTrace();
            messageLabel.setText("Database Error!");
            System.out.println(e.toString());
        }

    }
    private void loadBooks() {
        String query = "SELECT books.title FROM issue JOIN books ON issue.book_id = books.book_id WHERE issue.return_status = 'not returned'";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<String> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                bookList.add(rs.getString("title"));
            }
            issuedbooklistcombobox.setItems(bookList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBooks();
        issuedbooklistcombobox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String query="select student_id from issue join books on issue.book_id=books.book_id where books.title=? and issue.return_status='not returned'";
                
                try (Connection conn = DBConnection.connect();
                    PreparedStatement stmt = conn.prepareStatement(query)){
                    stmt.setString(1,newSelection);
                    ResultSet rs1 = stmt.executeQuery();
                    ObservableList<String> studentList = FXCollections.observableArrayList();
                    while(rs1.next()){
                         studentList.add(rs1.getString("student_id"));
                    }
                    studentlistcombobox.setItems(studentList);
                } catch (Exception e) {
                    e.printStackTrace();
                }                
                
            }
        });
    }    
    
}
