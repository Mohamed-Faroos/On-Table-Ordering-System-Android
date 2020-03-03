package com.otos.app.itemList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.otos.app.R;

public class MyHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtname,txtprice,txtquan;
    ImageView image;

    ItemClickListener2 itemClickListener;
    public MyHolder2(View itemview)
    {
        super(itemview);


        txtname= itemview.findViewById(R.id.txtpname);
        txtprice= itemview.findViewById(R.id.txtpprice);
        txtquan= itemview.findViewById(R.id.txtpquan);
        image=itemview.findViewById(R.id.itemImage);

        itemview.setOnClickListener(this);

    }

    public void setItemClickListner(ItemClickListener2 ic)
    {
        this.itemClickListener=ic;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }
}

