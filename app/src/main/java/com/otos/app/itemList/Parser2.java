package com.otos.app.itemList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.otos.app.mainFiles.Product;
import com.otos.app.mainFiles.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser2 extends AsyncTask<Void,Integer,Integer> {

    Context c;
    String data, table;
    RecyclerView rv;
    User us;
    ProgressDialog pd;
    ArrayList<Product> product = new ArrayList<>();
    MenuAdapter2 adapter;

    public Parser2(Context c, String data, RecyclerView rv, User us) {
        this.c = c;
        this.data = data;
        this.rv = rv;
        this.us = us;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Prase Data");
        pd.setMessage("Pasrsing...  Please wait ");
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        pd.dismiss();


        if (integer == 1) {
           adapter = new MenuAdapter2(c,product, us);
            rv.setAdapter(adapter);


        } else {
            Toast.makeText(c, "Unable to Parse", Toast.LENGTH_LONG).show();
        }

    }


 /*   private int parse() {
        try {
            JSONObject jo = new JSONObject(data);
            JSONArray res = jo.getJSONArray("server_response");
            for (int i = 0; i < res.length(); i++) {
                JSONObject w = res.getJSONObject(i);
                String name = w.getString("pname");
                String price = w.getString("price");
                String quantity = w.getString("quantity");
                String image = w.getString("image");


                pname.add(name);
                pprice.add(price);
                pimage.add(image);
                pimage.add(quantity);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }*/

   private int parse()
    {
        try
        {
            JSONArray ja=new JSONArray(data);
            JSONObject w=null;

            if(ja.length()>0)
            {
                for(int i=0; i<ja.length();i++)
                {
                    w=ja.getJSONObject(i);
                    String name = w.getString("pname");
                    int price = w.getInt("price");
                    int quantity = w.getInt("quantity");
                    String image = w.getString("image");

                    Product pro=new Product(name,price,quantity,image);
                    product.add(pro);

                }
                return 1;
            }else{
                return 0;
            }

        }catch(JSONException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}

