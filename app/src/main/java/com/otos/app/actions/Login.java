package com.otos.app.actions;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.otos.app.LoginActivity;
import com.otos.app.MenuActivity;
import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Login extends AsyncTask<String,Void,String> {

    Context context;
    String id;
    String password;
    AlertDialog alertDialog,alertDialog2;
    String log_url="http://192.168.8.102/OTOS/login.php";
    public Login(LoginActivity loginActivity, User us)
    {
        User user=us;
        id=user.getId();
        password=user.getPassword();
        context=loginActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result="";



        try {
                URL url = new URL(log_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("t_id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }


                bufferedReader.close();
                httpURLConnection.disconnect();


            } catch (Exception e) {
                e.printStackTrace();
            }


        return result;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();


        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Failed");


        alertDialog2=new AlertDialog.Builder(context).create();
        alertDialog2.setTitle("Logging In");
        alertDialog2.setMessage("Please Wait");

    }


    @Override
    protected void onPostExecute(String res) {
        super.onPostExecute(res);


        if(res.equals("success"))
        {
            alertDialog2.hide();
            Intent in = new Intent(context, MenuActivity.class);
            in.putExtra("table", id);
            context.startActivity(in);
            Toast toast = Toast.makeText(context, "Login Successfull", Toast.LENGTH_LONG);
            toast.show();

        }else{
            alertDialog2.hide();
            alertDialog.setMessage("Please Check Your Login Details");
            alertDialog.show();
        }


    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        alertDialog2.show();
    }
}
