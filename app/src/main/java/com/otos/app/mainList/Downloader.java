package com.otos.app.mainList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.otos.app.MenuActivity;
import com.otos.app.mainFiles.User;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader extends AsyncTask<String,Integer,String> {

    User us;
    Context c;
    String log_url,table;
    RecyclerView rv;

    ProgressDialog pd;

    public Downloader(MenuActivity c, String log_url, RecyclerView rv, User us) {
        this.c = c;
        this.log_url = log_url;
        this.rv = rv;
        this.us=us;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data=this.downloadData();
        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Loading Menu Items");
        pd.setMessage("Downloading....  Please wait");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        if(s!=null)
        {
            Parser p=new Parser(c,s,rv,us);
            p.execute();

        }else
        {
            Toast.makeText(c,"Unable to DOwnload",Toast.LENGTH_LONG).show();
        }
    }

    private String downloadData()  {
        InputStream is = null;
        String line = null;

        try {


            URL url = new URL(log_url);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            is=new BufferedInputStream(con.getInputStream());

            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuffer sb=new StringBuffer();

            if(br !=null)
            {
                while((line=br.readLine()) != null)
                {
                    sb.append(line+"\n");
                }
            }
            else{
                return null;
            }
            con.disconnect();
            br.close();
            return sb.toString();

        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if(is!=null)
            {
                try {
                    is.close();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return  null;
    }
}
