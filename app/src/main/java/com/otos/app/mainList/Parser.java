package com.otos.app.mainList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<String,Integer,Integer> {

    Context c;
    String data,table;
    RecyclerView rv;

    ProgressDialog pd;
    ArrayList<String> item=new ArrayList<>();
    ArrayList<String> id=new ArrayList<>();
    MenuAdapter adapter;

    public Parser(Context c, String data, RecyclerView rv) {
        this.c = c;
        this.data = data;
        this.rv = rv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Prase Data");
        pd.setMessage("Pasrsing...  Please wait ");
    }

    @Override
    protected Integer doInBackground(String... params) {

        table=params[0];
        return this.parse();


    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        pd.dismiss();

        if(integer==1)
        {
            adapter=new MenuAdapter(c,item,id,table);
            rv.setAdapter(adapter);
        }else{
            Toast.makeText(c,"Unable to Parse"+data,Toast.LENGTH_LONG).show();
        }

    }

    private int parse()
    {
        try
        {
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;

            item.clear();

            for(int i=0; i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String name=jo.getString("category");
                String cid=jo.getString("cid");
                item.add(name);
                id.add(cid);
            }
            return 1;
        }catch(JSONException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}

