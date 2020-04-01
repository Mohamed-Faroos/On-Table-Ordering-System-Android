package com.otos.app.cartList;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.otos.app.R;
import com.otos.app.itemList.ItemClickListener2;

public class MyHolder3 extends RecyclerView.ViewHolder{

    TextView txtname,txtquantity,txtstotal,txttype;
    ImageView image;
    Button btnRemove;

    public MyHolder3(View itemview)
    {
        super(itemview);


        txtname= itemview.findViewById(R.id.txtcname);
        image=itemview.findViewById(R.id.cimgView);
        txtquantity=itemview.findViewById(R.id.txtcquan);
        txtstotal=itemview.findViewById(R.id.txtstotal);
        txttype=itemview.findViewById(R.id.txttype);
        btnRemove=itemview.findViewById(R.id.btnRemove);


    }

}
