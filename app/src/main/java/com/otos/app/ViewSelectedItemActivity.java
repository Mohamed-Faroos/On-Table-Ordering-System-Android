package com.otos.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import com.otos.app.mainFiles.Product;

public class ViewSelectedItemActivity extends AppCompatActivity {

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_item);

        product=(Product) getIntent().getSerializableExtra("product");

        ImageView iv=findViewById(R.id.imgView);


        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        iv.setImageBitmap(decodedByte);
    }
}
