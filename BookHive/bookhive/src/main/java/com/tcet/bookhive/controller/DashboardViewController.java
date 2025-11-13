/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DashboardViewController implements Initializable {

    @FXML
    private Button btnlogout;

    @FXML
    private Button btnmanageusers;

    @FXML
    private Button btnretbk;

    @FXML
    private Button btnbooks;

    @FXML
    private Button btndashboard;

    @FXML
    private Button btnissbook;

    @FXML
    private Button btnoverdue;

    @FXML
    private Button btnstudents;

    @FXML
    private Label dashlabel;

    @FXML
    private BorderPane dsbdhbox;

    @FXML
    private StackPane mainContent;

    @FXML
    void handledashclick(ActionEvent event) throws IOException {
        Node DashboardContent = FXMLLoader.load(getClass().getResource("/com/tcet/bookhive/view/DashboardContent.fxml"));
        mainContent.getChildren().setAll(DashboardContent);
        dashlabel.setText("Dashboard");
    }

    @FXML
    void handlebooksclick(ActionEvent event) throws IOException {
        Node booksView = FXMLLoader.load(getClass().getResource("/com/tcet/bookhive/view/books.fxml"));
        mainContent.getChildren().setAll(booksView);
        dashlabel.setText("Books");
    }

    @FXML
    void handleissueclick(ActionEvent event) throws IOException {
        Node IssueView = FXMLLoader.load(getClass().getResource("/com/tcet/bookhive/view/IssueBook.fxml"));
        mainContent.getChildren().setAll(IssueView);
        dashlabel.setText("Issue Book");
    }

    @FXML
    void handleoverdueclick(ActionEvent event) throws IOException {
        Node OverdueView = FXMLLoader.load(getClass().getResource("/com/tcet/bookhive/view/Overdue.fxml"));
        mainContent.getChildren().setAll(OverdueView);
        dashlabel.setText("Overdue");
    }

    @FXML
    void handleretbkclick(ActionEvent event) throws IOException {
        Node ReturnBookContent = FXMLLoader.load(getClass().getResource("/com/tcet/bookhive/view/ReturnBook.fxml"));
        mainContent.getChildren().setAll(ReturnBookContent);
        dashlabel.setText("Return Book");
    }

    @FXML
    void handlestudentsclick(ActionEvent event) throws IOException {
        Node studentView = FXMLLoader.load(getClass().getResource("/com/tcet/bookhive/view/Students.fxml"));
        mainContent.getChildren().setAll(studentView);
        dashlabel.setText("Students");
    }

    @FXML
    void handlelogout(ActionEvent event) throws IOException {
        try {
            Connection con = DBConnection.connect();
            Statement stmt = con.createStatement();
            int rows1 = stmt.executeUpdate("update user_log join admin on admin.user_id=user_log.user_id set logout_time=now() where admin.status='logged in';");
            int rows2 = stmt.executeUpdate("update admin set status='logged out' where status='logged in';");
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tcet/bookhive/view/Login.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setMaximized(true);

            Stage dashStage = (Stage) dashlabel.getScene().getWindow();
            dashStage.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void handlemngusers(ActionEvent event) throws IOException {
        Node UserView = FXMLLoader.load(getClass().getResource("/com/tcet/bookhive/view/ManageUsers.fxml"));
        mainContent.getChildren().setAll(UserView);
        dashlabel.setText("Manage Users");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Node DashboardContent = null;
        try {
            DashboardContent = FXMLLoader.load(getClass().getResource("/com/tcet/bookhive/view/DashboardContent.fxml"));
        } catch (IOException ex) {
            System.getLogger(DashboardViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        mainContent.getChildren().setAll(DashboardContent);

        Stage primaryStage = new Stage();
            

    }

}
