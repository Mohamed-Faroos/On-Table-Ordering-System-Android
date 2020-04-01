package com.otos.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.otos.app.actions.ConfirmOrder;
import com.otos.app.cartList.cartAdapter;
import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.CartItems;
import com.otos.app.mainFiles.User;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    User us=new User();
    RecyclerView rv;
    Button btnconfirm;
    int netTotal;
    TextView netTot;
    ArrayList<Cart> cart= (ArrayList<Cart>) CartItems.getData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Cart");

        btnconfirm=findViewById(R.id.btnConfirm);

        Bundle bundle=getIntent().getExtras();
        us.setId(bundle.getString("table"));

        rv= findViewById(R.id.cartRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());


        for(int i=0;i<cart.size();i++)
        {
            int stotal=cart.get(i).getCount()*cart.get(i).getPrice();

            netTotal=netTotal+stotal;
        }

        netTot=findViewById(R.id.txttotal);
        netTot.setText("Rs. "+netTotal);



        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cart.size()>0)
                {

                    AlertDialog ad=AskConfirmOrder();
                    ad.show();

                }else{
                    Toast ts=Toast.makeText(CartActivity.this,"There is no Item in the Cart",Toast.LENGTH_SHORT);
                    ts.show();
                }

            }
        });


    }

    private AlertDialog AskConfirmOrder()
    {
        AlertDialog db = new AlertDialog.Builder(this,R.style.AlertDialog)

                .setTitle("Confirmation")
                .setMessage("Do you want to Confirm Your Order")
                .setIcon(R.drawable.ic_add_alert_black_24dp)


                .setPositiveButton("Confirm", new DialogInterface.OnClickListener()
                {

                    @Override
                            public void onClick(DialogInterface dialog, int which) {

                        ConfirmOrder co=new ConfirmOrder(CartActivity.this,us);
                        co.execute();

                            }
                        })
                    .setNegativeButton("Add More Items", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Intent i=new Intent(CartActivity.this,MenuActivity.class);
                            i.putExtra("table",us.getId());
                            startActivity(i);

                        }
                    })
                    .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    })
                    .create();



        return db;
    }

    @Override
    protected void onStart() {
        super.onStart();

        cartAdapter adapter=new cartAdapter(CartActivity.this,us);
        rv.setAdapter(adapter);
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
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home_settings) {
            Intent i=new Intent(this,MenuActivity.class);
            i.putExtra("table",us.getId());
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
