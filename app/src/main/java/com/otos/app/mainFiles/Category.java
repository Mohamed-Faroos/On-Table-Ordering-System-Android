package com.otos.app.mainFiles;

import java.io.Serializable;

public class Category implements Serializable {

    private String ID;
    private String category;

    public Category()
    {

        this.ID="";
        this.category="";
    }
    public Category(String ID, String category)
    {
        this.ID=ID;
        this.category=category;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
