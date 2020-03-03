package com.otos.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

import com.otos.app.itemList.Downloader2;
import com.otos.app.mainFiles.Category;
import com.otos.app.mainFiles.User;

public class ItemActivity extends AppCompatActivity {

    User us=new User();
    Category category=new Category();

    String log_url="http://192.168.8.102/OTOS/getItems.php";
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        category= (Category) getIntent().getSerializableExtra("category");
        us= (User) getIntent().getSerializableExtra("user");


        rv= findViewById(R.id.itemList);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());

        TextView head= findViewById(R.id.txtCategory);
        head.setText(category.getCategory());

    }

    @Override
    public void onStart() {
        super.onStart();

        Downloader2 d=new Downloader2(ItemActivity.this,log_url,rv,category,us);
        d.execute();
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
