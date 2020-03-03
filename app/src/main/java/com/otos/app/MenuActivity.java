package com.otos.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

import com.otos.app.mainFiles.User;
import com.otos.app.mainList.Downloader;

public class MenuActivity extends AppCompatActivity {
    User us=new User();
    String log_url="http://192.168.8.102/OTOS/getList.php";
    RecyclerView rv;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


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
}
