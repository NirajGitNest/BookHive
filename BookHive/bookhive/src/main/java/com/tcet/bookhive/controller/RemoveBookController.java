/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import com.tcet.bookhive.modal.bookModel;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class RemoveBookController implements Initializable {

    @FXML
    private ComboBox<String> booklistcombobox;
    
    @FXML
    private TextField copiesfield;

    @FXML
    private Label copieslabel;
    
    @FXML
    private TableView<bookModel> bookstable;


    @FXML
    private Button rembookbtn;

    @FXML
    private HBox rembookhbox;
    
    @FXML
    private Label messageLabel;

    @FXML
    void handleremovebook(ActionEvent event) {
        String bookname=booklistcombobox.getSelectionModel().getSelectedItem();
        Integer copies=Integer.parseInt(copiesfield.getText());
        if (bookname == null) {
            messageLabel.setText("Please select a book.");
            return;
        }
                
        String query1="update books set copies=copies-? where title=?";
        String query2="select copies from books where title=?";
        String query3="update books set status='archived',copies=0 where title=? ";        
        
        try (Connection conn = DBConnection.connect()){
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            
            stmt1.setInt(1, copies);
            stmt1.setString(2,bookname);
            stmt2.setString(1,bookname);

            int rowsaffected = stmt1.executeUpdate();
            if(rowsaffected>0) {
                messageLabel.setText(copies+" copies removed successfully!");                
            } else {
                messageLabel.setText("Failed to remove copies");
            }
            
            ResultSet rs=stmt2.executeQuery();
            int copy=0;
            while(rs.next()){
                copy=rs.getInt("copies");
            }
            if(copy<=0){
                PreparedStatement stmt3 = conn.prepareStatement(query3);
                stmt3.setString(1,bookname);
                stmt3.executeUpdate();                
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
            booklistcombobox.setItems(bookList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBooks("available");
        booklistcombobox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String query="select copies from books where title=?";
                
                try (Connection conn = DBConnection.connect();
                    PreparedStatement stmt = conn.prepareStatement(query)){
                    stmt.setString(1,newSelection);
                    ResultSet rs1 = stmt.executeQuery();
                    int copies=0;
                    while(rs1.next()){
                        copies=rs1.getInt("copies");
                    }
                    copieslabel.setText("Copies available: " + copies);
                } catch (Exception e) {
                    e.printStackTrace();
                }                
                
            } else {
                copieslabel.setText("Copy info not found: ");
            }
        });
    }    
    
}
