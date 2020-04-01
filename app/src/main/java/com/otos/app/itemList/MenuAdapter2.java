package com.otos.app.itemList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.otos.app.R;
import com.otos.app.ViewSelectedItemActivity;
import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.Product;
import com.otos.app.mainFiles.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MenuAdapter2 extends RecyclerView.Adapter<MyHolder2> {

    Context c;
    User us;
    ArrayList<Product> products;

    public MenuAdapter2(Context c, ArrayList<Product> product , User us) {
        this.c = c;
        this.products=product;
        this.us=us;

    }

    @Override
    public MyHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,null,false);
        MyHolder2 holder=new MyHolder2(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder2 holder, int position) {


        try {

            byte[] decodedString = Base64.decode(products.get(position).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            holder.txtname.setText(products.get(position).getName());
           holder.txtprice.setText("Rs." + products.get(position).getPrice());
           holder.image.setImageBitmap(decodedByte);


            holder.setItemClickListner(new ItemClickListener2() {
                @Override
                public void onItemClick(int pos) {
                    Toast.makeText(c, products.get(pos).getName(), Toast.LENGTH_LONG).show();

                    Product pro=new Product();

                    pro.setPid(products.get(pos).getPid());
                    pro.setName(products.get(pos).getName());
                    pro.setPrice(products.get(pos).getPrice());
                    pro.setQuantity(products.get(pos).getQuantity());
                    pro.setImage(products.get(pos).getImage());

                    Intent i = new Intent(c, ViewSelectedItemActivity.class);
                    i.putExtra("product", pro);
                    i.putExtra("user", us);
                    c.startActivity(i);

                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}

