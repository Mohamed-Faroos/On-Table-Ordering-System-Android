package com.otos.app.mainFiles;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String password;

    public User(String id, String password)
    {
        this.id=id;
        this.password=password;
    }

    public User() {

        this.id="";
        this.password="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
