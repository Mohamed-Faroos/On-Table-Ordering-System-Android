package com.otos.app.mainFiles;

import java.io.Serializable;

public class Cart extends Product implements Serializable {

    private int count;
    private int orderType;

    public Cart(String pid,String name, int price, int quantity, String image, int count, int orderType) {
        super(pid,name, price, quantity, image);
        this.setCount(count);
        this.setOrderType(orderType);
    }

    public Cart(int count, int orderType) {
        this.setCount(count);
        this.setOrderType(orderType);
    }

    public Cart() {
        super();
        this.setCount(0);
        this.setOrderType(0);
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
}
