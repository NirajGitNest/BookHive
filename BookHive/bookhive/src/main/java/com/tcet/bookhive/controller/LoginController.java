package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Platform;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        String query1="update admin set status='logged in' where user_id=?";
        String log="insert into user_log(user_id,login_time) values(?,now())";

        try (Connection conn = DBConnection.connect()){
            PreparedStatement stmt = conn.prepareStatement(query);
            PreparedStatement stmt1 = conn.prepareStatement(log);
            PreparedStatement stmt2 = conn.prepareStatement(query1);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                int user_id=rs.getInt("user_id");                
                stmt1.setInt(1, user_id);
                stmt2.setInt(1, user_id);
                int rowsaffected1=stmt1.executeUpdate();
                int rowsaffected2=stmt2.executeUpdate();
                if(rowsaffected1>0 && rowsaffected2>0)
                    System.out.println("log creation successfully");
                else
                    System.out.println("log creation failed");
                
                
                messageLabel.setText("Login Successful!");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tcet/bookhive/view/DashboardView.fxml"));
                Scene scene = new Scene(loader.load());

                //Create a new stage
                Stage dashboardStage = new Stage();
                dashboardStage.setTitle("BookHive - Dashboard");
                dashboardStage.setScene(scene);
                dashboardStage.setMaximized(true);
                dashboardStage.show();
                
                dashboardStage.setOnCloseRequest(event -> {
                    try (Connection con = DBConnection.connect(); Statement stmt5 = con.createStatement()) {
                        int rows1 = stmt5.executeUpdate("update user_log join admin on admin.user_id=user_log.user_id set logout_time=now() where admin.status='logged in';");
                        int rows2 = stmt5.executeUpdate("update admin set status='logged out' where status='logged in';");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                //Close the old login stage
                Stage loginStage = (Stage) usernameField.getScene().getWindow();
                loginStage.close();
                
            } else {
                messageLabel.setText("Invalid Username or Password!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Database Error!");
            System.out.println(e.toString());
        }
    }
}
