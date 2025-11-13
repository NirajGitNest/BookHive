package com.tcet.bookhive.modal;

import java.time.LocalDate;

public class UserModel {
    private int userid;
    private String name;
    private String username;
    private String status;
    private LocalDate datejoined;

    public UserModel(int userid, String name, String username, String status, LocalDate datejoined) {
        this.userid = userid;
        this.name = name;
        this.username = username;
        this.status = status;
        this.datejoined = datejoined;
    }

    public int getUserid() { return userid; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getStatus() { return status; }
    public LocalDate getDatejoined() { return datejoined; }
}

