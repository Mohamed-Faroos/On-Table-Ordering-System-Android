package com.otos.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.User;
import com.otos.app.mainList.Downloader;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    User us=new User();
    String log_url="http://192.168.8.102/OTOS/getList.php";
    RecyclerView rv;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Menu List");

        Bundle bundle=getIntent().getExtras();
        us.setId(bundle.getString("table"));


        rv= findViewById(R.id.menuView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onStart() {
        super.onStart();
        Downloader d=new Downloader(MenuActivity.this,log_url,rv,us);
        d.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Downloader d=new Downloader(MenuActivity.this,log_url,rv,us);
        d.execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Downloader d=new Downloader(MenuActivity.this,log_url,rv,us);
        d.execute();
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
            Intent i=new Intent(MenuActivity.this,CartActivity.class);
            i.putExtra("table",us.getId());
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
