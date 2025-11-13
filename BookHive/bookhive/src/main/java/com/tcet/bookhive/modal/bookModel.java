/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tcet.bookhive.modal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class bookModel {
    private final SimpleIntegerProperty ID,copies;
    private final SimpleStringProperty name, author, publisher, status, genre;

    public bookModel(Integer ID,String name, String author, String publisher,String genre, Integer copies, String availability) {
        this.ID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.genre=new SimpleStringProperty(genre);
        this.copies=new SimpleIntegerProperty(copies);        
        this.status = new SimpleStringProperty(availability);
    }
    public int getID() { return ID.get(); }
    public String getName() { return name.get(); }
    public String getAuthor() { return author.get(); }
    public String getPublisher() { return publisher.get(); }
    public String getGenre() { return genre.get(); }
    public int getCopies() { return copies.get(); } 
    public String getStatus() { return status.get(); }
}

