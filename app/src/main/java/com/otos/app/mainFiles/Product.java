package com.otos.app.mainFiles;

import java.io.Serializable;

public class Product implements Serializable {

    private String pid;
    private String name;
    private int price;
    private int quantity;
    private String image;

    public Product(String pid,String name, int price, int quantity, String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.pid=pid;
    }

    public Product() {
        this.name = "";
        this.price = 0;
        this.quantity = 0;
        this.image = "";
        this.pid="";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
