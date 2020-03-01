package com.otos.app.mainList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.otos.app.R;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtmenu;
    ItemClickListener itemClickListener;
    public MyHolder(View itemview)
    {
        super(itemview);


        txtmenu= itemview.findViewById(R.id.txtList);

        itemview.setOnClickListener(this);

    }

    public void setItemClickListner(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }
}
