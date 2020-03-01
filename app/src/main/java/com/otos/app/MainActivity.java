package com.otos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView loadimg;
    TextView loadtxt;
    ProgressBar pbload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadimg =(ImageView) findViewById(R.id.logo);
        loadtxt =(TextView) findViewById(R.id.heading);
        pbload =(ProgressBar) findViewById(R.id.progressBar);

        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.loading);
        final Intent i=new Intent(this,LoginActivity.class);
        loadimg.startAnimation(myanim);
        loadtxt.startAnimation(myanim);

        Thread time= new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }finally {
                    startActivity(i);
                }

            }
        };
        time.start();
    }


}
