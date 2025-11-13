/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import java.io.IOException;
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

public class AddUserController implements Initializable {

    @FXML
    private Button adduserbtn;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField namefield;

    @FXML
    private TextField passfield1;

    @FXML
    private TextField passfield2;

    @FXML
    private TextField usernamefield;

    @FXML
    void handleadduser(ActionEvent event) throws IOException {
        String name = namefield.getText();
        String username = usernamefield.getText();
        String pass1 = passfield1.getText();
        String pass2 = passfield2.getText();

        if (name.equals("")) {
            messageLabel.setText("Please enter your name.");
            return;
        }
        if (username.equals("")) {
            messageLabel.setText("Please enter your username.");
            return;
        }
        if (pass1.equals("") || pass2.equals("")) {
            messageLabel.setText("Please enter your password");
            return;
        }
        if (!pass1.equals(pass2)) {
            messageLabel.setText("Password didn't match kindly put the same password");
            return;
        }

        String query="insert into admin(name,username,password,status,date_joined) values(?,?,?,'logged out',CURDATE())";
            
        try{
            Connection con=DBConnection.connect();
            PreparedStatement stmt1=con.prepareStatement(query);
            stmt1.setString(1, name);
            stmt1.setString(2, username);
            stmt1.setString(3, pass1);
            
            int rowsaffected=stmt1.executeUpdate();
            if(rowsaffected>0){
                messageLabel.setText("user inserted Successfully");
            }else{
                messageLabel.setText("user insertion failed");
            }
            

            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
}
