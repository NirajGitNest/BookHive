/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tcet.bookhive.modal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class studentModel {
    private final SimpleStringProperty name, contact, dept;
    private final SimpleIntegerProperty ID;
    
    public studentModel(Integer ID,String name, String contact, String dept) {
        this.ID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.contact = new SimpleStringProperty(contact);
        this.dept = new SimpleStringProperty(dept);
    }
    public int getID() { return ID.get(); }
    public String getName() { return name.get(); }
    public String getContact() { return contact.get(); }
    public String getDept() { return dept.get(); }
}
