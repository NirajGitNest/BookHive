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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class RemoveUserController implements Initializable {

    @FXML
    private Label messageLabel;

    @FXML
    private Button remuserbtn;

    @FXML
    private ComboBox<String> userlistcombobox;

    @FXML
    void handleremoveuser(ActionEvent event) {
        String user_name=userlistcombobox.getSelectionModel().getSelectedItem();
        if (user_name == null) {
            messageLabel.setText("Please select name of the user");
            return;
        }
        
        String query="Delete from admin where name=? ";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user_name);

            int rowsaffected = stmt.executeUpdate();
            if(rowsaffected>0) {
                messageLabel.setText("User removed successfully!");                
            } else {
                messageLabel.setText("Failed to remove User");
            }
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }
    private void loadUsers() {
        String query = "SELECT name FROM admin";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<String> userList = FXCollections.observableArrayList();
            while (rs.next()) {
                userList.add(rs.getString("name"));
            }
            userlistcombobox.setItems(userList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUsers();
    }    
    
}
