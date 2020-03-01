package com.otos.app.mainList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.otos.app.ItemActivity;
import com.otos.app.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MyHolder> {


    Context c;
    ArrayList<String> items;
    ArrayList<String> id;
    String table;

    public MenuAdapter(Context items, ArrayList<String> c,ArrayList<String> cid,String t) {
        this.items = c;
        this.id=cid;
        this.c = items;
        this.table=t;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.menulist,parent,false);
        MyHolder holder=new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {



        holder.txtmenu.setText(items.get(position));

        holder.setItemClickListner(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(c,id.get(pos),Toast.LENGTH_LONG).show();

                Intent i=new Intent(c, ItemActivity.class);
                i.putExtra("cid",id.get(pos));
                i.putExtra("category",items.get(pos));
                i.putExtra("table",table);
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
