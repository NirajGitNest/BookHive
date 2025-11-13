package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentFormController implements Initializable {

    @FXML
    private Button addstdbtn;

    @FXML
    private TextField studentcont;

    @FXML
    private TextField studentdept;

    @FXML
    private TextField studentid;

    @FXML
    private TextField studentname;

    @FXML
    private Label messageLabel;

    @FXML
    void handleaddstudent(ActionEvent event) {
        Integer id = Integer.parseInt(studentid.getText());
        String name = studentname.getText();
        String contact = studentcont.getText();
        String dept = studentdept.getText();

        String query="Insert into students(student_id,name,contact,dept) values(?,?,?,?) ";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, contact);
            stmt.setString(4, dept);

            int rowsaffected = stmt.executeUpdate();
            if(rowsaffected>0) {
                messageLabel.setText("Student added successfully!");                
            } else {
                messageLabel.setText("Failed to add Student");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Database Error!");
            System.out.println(e.toString());
        }        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
