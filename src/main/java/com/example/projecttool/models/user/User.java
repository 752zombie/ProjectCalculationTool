package com.example.projecttool.models.user;

// Magnus

public class User {
    private int id;
    private String name;
    private String eMail;
    private String password;


    public User(String name, String eMail, String password) {
        this.name = name;
        this.eMail = eMail;
        this.password = password;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
