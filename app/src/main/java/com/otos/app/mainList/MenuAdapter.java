package com.otos.app.mainList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.otos.app.ItemActivity;
import com.otos.app.R;
import com.otos.app.mainFiles.Category;
import com.otos.app.mainFiles.User;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MyHolder> {

    User us;
    Context c;
    ArrayList<Category> category;
    Category cate;


    public MenuAdapter(Context items, ArrayList<Category> c,User us) {
        this.category = c;
        this.c = items;
        this.us=us;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.menulist,parent,false);
        MyHolder holder=new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {



        holder.txtmenu.setText(category.get(position).getCategory());

        holder.setItemClickListner(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                Toast.makeText(c,us.getId(),Toast.LENGTH_LONG).show();

                cate=new Category();
                cate.setID(category.get(pos).getID());
                cate.setCategory(category.get(pos).getCategory());

                Intent i=new Intent(c, ItemActivity.class);

                i.putExtra("category", cate);
                i.putExtra("user",us);
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }
}
