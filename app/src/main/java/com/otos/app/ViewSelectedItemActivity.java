package com.otos.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.otos.app.itemList.Downloader2;
import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.CartItems;
import com.otos.app.mainFiles.Product;
import com.otos.app.mainFiles.User;

import java.util.ArrayList;

public class ViewSelectedItemActivity extends AppCompatActivity {

    Product product;
    User us;
    Cart ct;
    RadioButton tbl,taw;
    Button btnAdd;
    int type,itemCount;

    EditText count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_item);

        product=(Product) getIntent().getSerializableExtra("product");
        us= (User) getIntent().getSerializableExtra("user");




        ImageView iv=findViewById(R.id.imgView);
        TextView price=findViewById(R.id.txtPrice);
        TextView quan=findViewById(R.id.txtQuantity);
        tbl= findViewById(R.id.rbt);
        taw=findViewById(R.id.rbtaw);
        count=findViewById(R.id.txtcount);
        btnAdd=findViewById(R.id.btnAdd);


        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        iv.setImageBitmap(decodedByte);
        price.setText("Price : "+product.getPrice()+"/-");
        quan.setText("Available Quantity : "+product.getQuantity());
        setTitle(product.getName());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemCount=Integer.parseInt(count.getText().toString());

                if(itemCount>product.getQuantity())
                {
                    Toast tost=Toast.makeText(ViewSelectedItemActivity.this,"Only "+product.getQuantity()+
                            " "+product.getName()+" are available",Toast.LENGTH_LONG);
                    tost.show();
                }else {
                    if (tbl.isChecked()) {
                        type = 0;

                    } else {
                        type = 1;

                    }
                    int cnt = Integer.parseInt(count.getText().toString());
                    ct = new Cart();
                    ct.setPid(product.getPid());
                    ct.setName(product.getName());
                    ct.setPrice(product.getPrice());
                    ct.setImage(product.getImage());
                    ct.setCount(cnt);
                    ct.setOrderType(type);
                    CartItems.addItem(ct);

                    Intent i = new Intent(ViewSelectedItemActivity.this, CartActivity.class);
                    i.putExtra("table", us.getId());
                    startActivity(i);

                    Toast tost = Toast.makeText(ViewSelectedItemActivity.this, "Item Added", Toast.LENGTH_SHORT);
                    tost.show();
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(this,CartActivity.class);
            i.putExtra("table",us.getId());
            startActivity(i);
            return true;
        }else{
            Intent i=new Intent(this,MenuActivity.class);
            i.putExtra("table",us.getId());
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }

}
