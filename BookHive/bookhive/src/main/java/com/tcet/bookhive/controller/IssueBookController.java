/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class IssueBookController implements Initializable {

    @FXML
    private HBox Issuehbox;

    @FXML
    private ComboBox<String> bookcombobox;

    @FXML
    private Button btnIssueBook;

    @FXML
    private DatePicker issuedatepicker;

    @FXML
    private Label messageLabel;

    @FXML
    private DatePicker returndatepicker;

    @FXML
    private ComboBox<Integer> studentcombobox;

    @FXML
    void handleIssueBook(ActionEvent event) {
        String bookname=bookcombobox.getSelectionModel().getSelectedItem();
        Integer studentID=studentcombobox.getSelectionModel().getSelectedItem();
        LocalDate issuedate=issuedatepicker.getValue();
        LocalDate returndate=returndatepicker.getValue();
        
        if (bookname == null) {
            messageLabel.setText("Please select a book.");
            return;
        }
        if (studentID == null) {
            messageLabel.setText("Please select Student ID.");
            return;
        }
        
        String query1="select book_id,copies from books where title=?";
        String query2="insert into issue(book_id,student_id,issue_date,return_date,return_status) values(?,?,?,?,'not returned')";  
        String query3="update books set copies=copies-1 where title=?";
        String query4="update books set status='issued' where book_id=?";        
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt1 = conn.prepareStatement(query1);
             PreparedStatement stmt2 = conn.prepareStatement(query2);
             PreparedStatement stmt3 = conn.prepareStatement(query3);
             PreparedStatement stmt4 = conn.prepareStatement(query4);) {

            stmt1.setString(1, bookname);
            ResultSet rs1=stmt1.executeQuery();
            
            int bookID = 0;
            int copies=0;
            while(rs1.next()){
                bookID= rs1.getInt("book_id");
                copies=rs1.getInt("copies");
            }
            stmt2.setInt(1,bookID);
            stmt2.setInt(2,studentID);
            stmt2.setDate(3,java.sql.Date.valueOf(issuedate));
            stmt2.setDate(4,java.sql.Date.valueOf(returndate));
            stmt3.setString(1,bookname);
            
            int rowsaffected1 = stmt2.executeUpdate();
            int rowsaffected2= stmt3.executeUpdate();
            
            if(rowsaffected1>0 && rowsaffected2>0) {
                messageLabel.setText("Book issued successfully!");  
                bookcombobox.getSelectionModel().clearSelection();
                loadBooks("available");
                studentcombobox.getSelectionModel().clearSelection();
                loadStudents();
                issuedatepicker.setValue(null);
                returndatepicker.setValue(null);
            } else {
                messageLabel.setText("Failed to issue book");
            }
            
            if(copies-1==0){
                stmt4.setInt(1, bookID);
                stmt4.executeUpdate();
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Database Error!");
            System.out.println(e.toString());
        }
        

    }
    private void loadBooks(String filter) {
        String query = filter.equals("available")
            ? "SELECT title FROM books WHERE status = 'available'"
            : "SELECT title FROM books";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<String> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                bookList.add(rs.getString("title"));
            }
            bookcombobox.setItems(bookList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadStudents() {
        String query = "SELECT student_id FROM students";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<Integer> StudentList = FXCollections.observableArrayList();
            while (rs.next()) {
                StudentList.add(rs.getInt("student_id"));
            }
            studentcombobox.setItems(StudentList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBooks("available");
        loadStudents();
    }    
    
}
