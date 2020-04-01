package com.otos.app.mainList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.Category;
import com.otos.app.mainFiles.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<String,Integer,Integer> {
    User us;
    Context c;
    String data;
    RecyclerView rv;

    ProgressDialog pd;
    ArrayList<Category> category=new ArrayList<>();
    Category cate;
    MenuAdapter adapter;
    public Parser(Context c, String data, RecyclerView rv,User us) {
        this.c = c;
        this.data = data;
        this.rv = rv;
        this.us=us;
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

        return this.parse();


    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        pd.dismiss();

        if(integer==1)
        {
            adapter=new MenuAdapter(c,category,us);
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

            category.clear();

            for(int i=0; i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String name=jo.getString("category");
                String cid=jo.getString("cid");
                cate=new Category(cid,name);
                category.add(cate);
            }
            return 1;
        }catch(JSONException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}

