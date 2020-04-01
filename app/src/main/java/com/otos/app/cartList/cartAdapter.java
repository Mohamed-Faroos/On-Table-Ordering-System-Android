package com.otos.app.cartList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.otos.app.CartActivity;
import com.otos.app.R;
import com.otos.app.ViewSelectedItemActivity;
import com.otos.app.itemList.ItemClickListener2;
import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.CartItems;
import com.otos.app.mainFiles.User;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<MyHolder3> {

    Context c;
    User us;
    ArrayList<Cart> cart= (ArrayList<Cart>) CartItems.getData();


    public cartAdapter(CartActivity cartActivity, User us) {
        this.c=cartActivity;
        this.us=us;

    }

    @Override
    public MyHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlist,null,false);
        MyHolder3 holder=new MyHolder3(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder3 holder, final int position) {


        try {

            byte[] decodedString = Base64.decode(cart.get(position).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            int total=cart.get(position).getPrice()*cart.get(position).getCount();

            String type="";

            if(cart.get(position).getOrderType()==0)
            {
                type="Dine In";

            }else{
                type="Take Away";
            }

            holder.txtname.setText(cart.get(position).getName());
            holder.image.setImageBitmap(decodedByte);
            holder.txtquantity.setText("X"+cart.get(position).getCount());
            holder.txtstotal.setText("Rs."+total);
            holder.txttype.setText(type);

            holder.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog ad=AskRemoveOrder(position,cart.get(position).getName());
                    ad.show();
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AlertDialog AskRemoveOrder(int pos,String pname)
    {
       final int position =pos;
       final String name=pname;

        AlertDialog db = new AlertDialog.Builder(c,R.style.AlertDialog)

                .setTitle("Remove Order")
                .setMessage("Do you want to Remove ''"+name+"'' Order")
                .setIcon(R.drawable.ic_delete_black_24dp)


                .setPositiveButton("Remove", new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cart.remove(position);
                        Intent i=new Intent(c, CartActivity.class);
                        i.putExtra("table",us.getId());
                        c.startActivity(i);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();



        return db;
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }
}
