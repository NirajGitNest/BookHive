/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tcet.bookhive.controller;

import com.tcet.bookhive.modal.DBConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import com.tcet.bookhive.modal.bookModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BooksController implements Initializable {
    
    @FXML
    private Button btnaddbk;

    @FXML
    private Button btnremobk;
    
    @FXML private TableView<bookModel> bookstable;

    @FXML
    private TableColumn<bookModel, String> authcolumn;

    @FXML
    private TableColumn<bookModel, String> statuscolumn;
    
    @FXML
    private TableColumn<bookModel, Integer> copiescolumn;

    @FXML
    private TableColumn<bookModel, String> genrecolumn;

    @FXML
    private HBox bookshbox;

    @FXML
    private TableColumn<bookModel, String> idcolumn;

    @FXML
    private TableColumn<bookModel, String> namecolumn;

    @FXML
    private TableColumn<bookModel, String> publcolumn;

    @FXML
    private TextField searchfield;
    
    private ObservableList<bookModel> bookList = FXCollections.observableArrayList();
    
    @FXML
    void handleadbkclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tcet/bookhive/view/AddBook.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Add Book");
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait(); 
        loadbooks();
    }

    @FXML
    void handlerembkclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tcet/bookhive/view/RemoveBook.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Remove Book");
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait(); 
        loadbooks();
    }


    @FXML
    void handleSearch(ActionEvent event) {
        String keyword = searchfield.getText().trim().toLowerCase();
        ObservableList<bookModel> filteredList = FXCollections.observableArrayList();

        String query = "SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? OR LOWER(publisher) LIKE ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String genre=rs.getString("genre");
                int copies = rs.getInt("copies");
                String status = rs.getString("status");

                filteredList.add(new bookModel(id, name, author, publisher,genre,copies, status));
            }

            bookstable.setItems(filteredList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadbooks() {
        ObservableList<bookModel> bookList = FXCollections.observableArrayList();
        
        String query = "SELECT * FROM books";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String genre=rs.getString("genre");
                int copies = rs.getInt("copies");
                String status = rs.getString("status");
                
                bookList.add(new bookModel(id, name, author, publisher, genre, copies, status));
            }
            bookstable.setItems(bookList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadbooks();
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authcolumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publcolumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        genrecolumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        copiescolumn.setCellValueFactory(new PropertyValueFactory<>("copies"));
        statuscolumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        searchfield.textProperty().addListener((obs, oldText, newText) -> {
        handleSearch(null);
        });
    }       
}
