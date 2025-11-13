/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import com.tcet.bookhive.modal.UserModel;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sahil
 */
public class ManageUsersController implements Initializable {
    
    @FXML
    private Button btnadduser;

    @FXML
    private Button btnremuser;

    @FXML
    private TableColumn<UserModel, LocalDate> datejoincolumn;

    @FXML
    private TableColumn<UserModel, Integer> idcolumn;

    @FXML
    private TableColumn<UserModel, String> namecolumn;

    @FXML
    private TextField searchfield;

    @FXML
    private TableColumn<UserModel, String> statuscolumn;

    @FXML
    private TableColumn<UserModel, String> usernamecolumn;

    @FXML
    private TableView<UserModel> usertable;

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    void handleadduserclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tcet/bookhive/view/AddUser.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Add User");
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();    
        loadUsers();
    }

    @FXML
    void handleremuserclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tcet/bookhive/view/RemoveUser.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Remove User");
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();    
        loadUsers();
    }
    private void loadUsers() {
        ObservableList<UserModel> userList = FXCollections.observableArrayList();
        
        String query = "SELECT * FROM admin";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String status = rs.getString("status");
                LocalDate date_joined = rs.getObject("date_joined",LocalDate.class);
                userList.add(new UserModel(user_id, name, username, status, date_joined));
            }
            usertable.setItems(userList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUsers();
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernamecolumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        statuscolumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        datejoincolumn.setCellValueFactory(new PropertyValueFactory<>("datejoined"));
    }    
    
}
