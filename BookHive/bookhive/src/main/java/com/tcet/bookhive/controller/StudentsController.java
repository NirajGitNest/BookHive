package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import com.tcet.bookhive.modal.studentModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class StudentsController implements Initializable {

    @FXML
    private Button addstdbtn;

    @FXML
    private TableView<studentModel> studenttable;

    @FXML
    private TableColumn<studentModel,String> contcolumn;

    @FXML
    private TableColumn<studentModel,String> deptcolumn;

    @FXML
    private TableColumn<studentModel,String> idcolumn;

    @FXML
    private TableColumn<studentModel,String> namecolumn;

    @FXML
    private TextField searchfield;

    @FXML
    private Button stdsearchbtn;

    @FXML
    private HBox studenthbox;
    
    
    @FXML
    void handleSearch(ActionEvent event) {
        String keyword = searchfield.getText().trim().toLowerCase();
        ObservableList<studentModel> filteredList = FXCollections.observableArrayList();

        String query = "SELECT * FROM students WHERE LOWER(name) LIKE ? OR CAST(student_id AS CHAR) LIKE ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                String dept = rs.getString("dept");

                filteredList.add(new studentModel(id, name, contact, dept));
            }

            studenttable.setItems(filteredList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleaddstd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tcet/bookhive/view/AddStudentForm.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Add Student");
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait(); 
        loadStudents();
    }
    private void loadStudents() {
        ObservableList<studentModel> studentList = FXCollections.observableArrayList();
        
        String query = "SELECT * FROM students";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                String dept = rs.getString("dept");
                
                studentList.add(new studentModel(id, name, contact, dept));
            }
            studenttable.setItems(studentList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadStudents();
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        contcolumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        deptcolumn.setCellValueFactory(new PropertyValueFactory<>("dept"));
        searchfield.textProperty().addListener((obs, oldText, newText) -> {
        handleSearch(null);
        });
    }    
}    
    
