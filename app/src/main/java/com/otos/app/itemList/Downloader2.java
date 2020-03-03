package com.otos.app.itemList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.otos.app.ItemActivity;
import com.otos.app.mainFiles.Category;
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

public class Downloader2 extends AsyncTask<String,Void,String> {
    Context c;
    String urlAddress;
    RecyclerView rv;
    ProgressDialog pd;
    Category cate;
    User us;

    public Downloader2(ItemActivity itemActivity, String log_url, RecyclerView rv, Category category, User us) {
        this.c=itemActivity;
        this.urlAddress=log_url;
        this.rv=rv;
        this.cate=category;
        this.us=us;
    }

    @Override
    protected String doInBackground(String... strings) {


            try{
                String cid=cate.getID();

                URL url=new URL(urlAddress);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("cid","UTF-8")+"="+URLEncoder.encode(cid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String line="";
                StringBuffer sb=new StringBuffer();

                if(bufferedReader !=null)
                {
                    while((line=bufferedReader.readLine()) != null)
                    {
                        sb.append(line+"\n");
                    }
                }
                else{
                    return null;
                }
                httpURLConnection.disconnect();
                bufferedReader.close();
                return sb.toString();

            }catch (Exception e)
            {
                e.printStackTrace();
            }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Loading Items");
        pd.setMessage("Downloading....  Please wait");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        if(s!=null)
        {
           Parser2 p=new Parser2(c,s,rv,us);
            p.execute();

        }else
        {
            Toast.makeText(c,"Unable to DOwnload",Toast.LENGTH_LONG).show();
        }
    }
}
