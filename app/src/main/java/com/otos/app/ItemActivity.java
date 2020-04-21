package com.otos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.otos.app.itemList.Downloader2;
import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.Category;
import com.otos.app.mainFiles.User;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    User us=new User();
    Category category=new Category();

    String log_url="http://192.168.8.103/OTOS/getItems.php";
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


        setTitle(category.getCategory());

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(this,CartActivity.class);
            i.putExtra("table",us.getId());
            startActivity(i);
            return true;
        }else{
            Intent i=new Intent(ItemActivity.this,MenuActivity.class);
            i.putExtra("table",us.getId());
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }
}
