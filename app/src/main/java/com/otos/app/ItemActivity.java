package com.otos.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

import com.otos.app.itemList.Downloader2;

public class ItemActivity extends AppCompatActivity {

    public String table;
    String category,cid;
    String log_url="http://192.168.8.102/OTOS/getItems.php";
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        Bundle bundle=getIntent().getExtras();
        cid=bundle.getString("cid");
        category=bundle.getString("category");
        table=bundle.getString("table");


        rv= findViewById(R.id.itemList);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());

        TextView head= findViewById(R.id.txtCategory);
        head.setText(category);

    }

    @Override
    public void onStart() {
        super.onStart();
        String type = "login";

        Downloader2 d=new Downloader2(ItemActivity.this,log_url,rv);
        d.execute(type,category,table,cid);
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
}
