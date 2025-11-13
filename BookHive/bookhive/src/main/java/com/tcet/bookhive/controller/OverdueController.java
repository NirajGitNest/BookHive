package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import com.tcet.bookhive.modal.OverdueModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class OverdueController implements Initializable {

    @FXML
    private TableColumn<OverdueModel, Integer> dayslatecolumn;

    @FXML
    private TableColumn<OverdueModel, Integer> fineamountcolumn;

    @FXML
    private TableColumn<OverdueModel, String> finestatuscolumn;

    @FXML
    private TableColumn<OverdueModel, String> bknamecolumn;

    @FXML
    private TableColumn<OverdueModel, LocalDate> issuedatecolumn;
    
    @FXML
    private TableColumn<OverdueModel, LocalDate> returndatecolumn;
    
    @FXML
    private TableColumn<OverdueModel, String> stdnamecolumn;

    @FXML
    private HBox overduehbox;

    @FXML
    private TableView<OverdueModel> overduetable;
    
    
    
    private void findOverdue() {
        String query1 = "SELECT issue_id,book_id,student_id,issue_date,return_date,DATEDIFF(CURDATE(),return_date) AS days_late FROM issue WHERE return_status = 'not returned' AND CURDATE() > return_date";
        String query2="insert into overdue(issue_id,book_id,student_id,days_late,fine_amount,fine_status) values(?,?,?,?,?,'unpaid')";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt1 = conn.prepareStatement(query1);
             PreparedStatement stmt2 = conn.prepareStatement(query2);
             ResultSet rs = stmt1.executeQuery()) {

            while (rs.next()) {
                int issue_id=rs.getInt("issue_id");
                int book_id=rs.getInt("book_id");
                int student_id=rs.getInt("student_id");
                int days_late=rs.getInt("days_late");
                int fine_amount=days_late*5;
                
                String checkQuery = "SELECT COUNT(*) FROM overdue WHERE issue_id = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setInt(1, issue_id);
                ResultSet checkRs = checkStmt.executeQuery();
                checkRs.next();
                if (checkRs.getInt(1) == 0) {
                    stmt2.setInt(1, issue_id);
                    stmt2.setInt(2, book_id);
                    stmt2.setInt(3, student_id);
                    stmt2.setInt(4, days_late);
                    stmt2.setInt(5, fine_amount);
                    stmt2.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadOverdueBooks() {
        ObservableList<OverdueModel> OverdueList = FXCollections.observableArrayList();
        
        String query1 = "SELECT * FROM overdue";
        String query2="select title from books where book_id=?";
        String query3="select name from students where student_id=?";
        String query4="select issue_date,return_date from issue where book_id=?";

        try (Connection conn = DBConnection.connect()) {            
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            PreparedStatement stmt3 = conn.prepareStatement(query3);
            PreparedStatement stmt4 = conn.prepareStatement(query4);
            
            String book_name = null;
            String student_name = null;
            LocalDate issue_date = null;
            LocalDate return_date = null;
            int book_id=0;
            int student_id=0;
            int days_late=0;
            int fine_amount=0;
            String fine_status = null;
            
            ResultSet rs1 = stmt1.executeQuery();                     
            while (rs1.next()) {
                book_id = rs1.getInt("book_id");
                student_id = rs1.getInt("student_id");
                days_late = rs1.getInt("days_late");
                fine_amount = rs1.getInt("fine_amount");
                fine_status = rs1.getString("fine_status");
            
                stmt2.setInt(1,book_id);
                ResultSet rs2 = stmt2.executeQuery();
                while(rs2.next()){
                    book_name=rs2.getString("title");
                }

                stmt3.setInt(1,student_id);
                ResultSet rs3 = stmt3.executeQuery();
                while(rs3.next()){
                    student_name=rs3.getString("name");
                }

                stmt4.setInt(1,book_id);
                ResultSet rs4 = stmt4.executeQuery();
                while(rs4.next()){
                    issue_date=rs4.getObject("issue_date",LocalDate.class);
                    return_date=rs4.getObject("return_date",LocalDate.class);
                }
                OverdueList.add(new OverdueModel(book_name,student_name, issue_date, return_date,days_late,fine_amount,fine_status));
            }
            
            overduetable.setItems(OverdueList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        findOverdue();
        loadOverdueBooks();
        bknamecolumn.setCellValueFactory(new PropertyValueFactory<>("bkname"));
        stdnamecolumn.setCellValueFactory(new PropertyValueFactory<>("stdname"));
        issuedatecolumn.setCellValueFactory(new PropertyValueFactory<>("issuedate"));
        returndatecolumn.setCellValueFactory(new PropertyValueFactory<>("returndate"));
        dayslatecolumn.setCellValueFactory(new PropertyValueFactory<>("dayslate"));
        fineamountcolumn.setCellValueFactory(new PropertyValueFactory<>("fineamount"));
        finestatuscolumn.setCellValueFactory(new PropertyValueFactory<>("finestatus"));
    }    
    
}
