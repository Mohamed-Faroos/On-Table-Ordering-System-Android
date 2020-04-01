package com.otos.app.mainFiles;

import java.util.ArrayList;
import java.util.List;

public class CartItems extends Cart {

    private static List<Cart> data = new ArrayList<>();


    public static void addItem(Cart item) {
        data.add(item);
    }

    public static List<Cart> getData() {
        return data;
    }
}
