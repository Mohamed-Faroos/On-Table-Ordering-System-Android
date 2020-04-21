package com.otos.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.otos.app.actions.Login;
import com.otos.app.mainFiles.User;

public class LoginActivity extends AppCompatActivity {

    EditText txtid,txtpassword;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtid= findViewById(R.id.txtId);
        txtpassword=findViewById(R.id.txtPassword);
        btnSubmit=findViewById(R.id.button);

    }

    public void Logon(View view) {

        String t_id = txtid.getText().toString();
        String password = txtpassword.getText().toString();
        String type = "login";


        if (t_id.isEmpty() && !password.isEmpty())
        {
            txtid.setError("Enter Username");
        } else
            if (t_id.isEmpty()) {

            txtid.setError("Enter Username");

            } else
                if (!t_id.isEmpty() && password.isEmpty()) {

                txtpassword.setError("Enter Password");

                } else
                    if (password.isEmpty()) {

                        txtpassword.setError("Enter Password");

                    } else {

                    User us=new User();
                    us.setId(t_id);
                    us.setPassword(password);


            Login login=new Login(this,us);
            login.execute();

        }
    }

    @Override
    public void onStart() {
        super.onStart();

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

}
