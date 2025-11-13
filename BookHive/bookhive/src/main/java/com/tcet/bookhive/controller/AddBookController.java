/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.scene.layout.HBox;

public class AddBookController implements Initializable {
    @FXML
    private Button addbookbtn;

    @FXML
    private HBox addbookhbox;

    @FXML
    private TextField authnamefield;

    @FXML
    private TextField booknamefield;

    @FXML
    private TextField publnamefield;
    
    @FXML
    private TextField copiesfield;

    @FXML
    private TextField genrefield;
    
    @FXML
    private Label messageLabel;

    @FXML
    void handleaddbook(ActionEvent event) {
        String bookname=booknamefield.getText();
        String authname=authnamefield.getText();
        String publname=publnamefield.getText();
        String genre=genrefield.getText();
        Integer copies=Integer.parseInt(copiesfield.getText());
        
        String query="Insert into books(title,author,publisher,genre,copies) values(?,?,?,?,?) ";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bookname);
            stmt.setString(2, authname);
            stmt.setString(3, publname);
            stmt.setString(4, genre);
            stmt.setInt(5, copies);

            int rowsaffected = stmt.executeUpdate();
            if(rowsaffected>0) {
                messageLabel.setText("Book added successfully!");                
            } else {
                messageLabel.setText("Failed to add book");
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
