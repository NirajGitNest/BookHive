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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DashboardContentController implements Initializable {

    @FXML
    private VBox bkvbox;
    
    @FXML
    private Label userLabel;

    @FXML
    private Label dashlabel;

    @FXML
    private HBox dsbdhbox;

    @FXML
    private VBox issvbox;

    @FXML
    private StackPane mainContent;

    @FXML
    private Label nobklabel;

    @FXML
    private Label noisslabel;

    @FXML
    private Label nostdlabel;

    @FXML
    private VBox stdvbox;
    
    private void loadDashboard() {
        String query1 = "select count(*) from students";
        String query2="select sum(copies) from books";
        String query3="select count(*) from issue where return_status='not returned'";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt1 = conn.prepareStatement(query1);
             PreparedStatement stmt2 = conn.prepareStatement(query2);
             PreparedStatement stmt3 = conn.prepareStatement(query3);
             ResultSet rs1 = stmt1.executeQuery();
             ResultSet rs2 = stmt2.executeQuery();
             ResultSet rs3 = stmt3.executeQuery()) {
            
            Statement stmt5=conn.createStatement();
            ResultSet newrs=stmt5.executeQuery("select name from admin where status='logged in';");
            
            String name=null;
            if(newrs.next())
            name=newrs.getString("name");
            
            userLabel.setText("Welcome, " +name.split(" ")[0]);
            
            while (rs1.next()) {
                int totstd=rs1.getInt(1);
                nostdlabel.setText(String.valueOf(totstd));
            }
            while (rs2.next()) {
                int totbks=rs2.getInt(1);
                nobklabel.setText(String.valueOf(totbks));
            }
            while (rs3.next()) {
                int totissbks=rs3.getInt(1);
                noisslabel.setText(String.valueOf(totissbks));
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDashboard();
    }        
}
