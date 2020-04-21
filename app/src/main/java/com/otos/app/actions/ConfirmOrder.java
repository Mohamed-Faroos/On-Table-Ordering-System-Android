package com.otos.app.actions;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.otos.app.MenuActivity;
import com.otos.app.mainFiles.Cart;
import com.otos.app.mainFiles.CartItems;
import com.otos.app.mainFiles.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ConfirmOrder extends AsyncTask<String,Void,String> {

    Context c;
    User us;
    ArrayList<Cart> cart= (ArrayList<Cart>) CartItems.getData();
    ProgressDialog pd;
    String log_url="http://192.168.8.103/OTOS/order.php";

    public ConfirmOrder(Context c,User us)
    {
        this.us=us;
        this.c=c;

    }
    @Override
    protected String doInBackground(String... strings) {
       try{

            URL url=new URL(log_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            JSONArray jsonArray = new JSONArray();
            for (Cart cart : cart) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("pid", cart.getPid());
                jsonObject.put("name", cart.getName());
                jsonObject.put("price", cart.getPrice());
                jsonObject.put("count", cart.getCount());
                jsonObject.put("type", cart.getOrderType());
                jsonArray.put(jsonObject);
            }

            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("t_id", "UTF-8") + "=" + URLEncoder.encode(us.getId(), "UTF-8") +
                                "&" + URLEncoder.encode("cart", "UTF-8") + "=" + URLEncoder.encode(jsonArray.toString(), "UTF-8");
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
            return sb.toString().trim();

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return null;


    /*    JSONArray jsonArray = new JSONArray();
      try{
          for (Cart cart : cart) {
              JSONObject jsonObject = new JSONObject();
              jsonObject.put("pid", cart.getPid());
              jsonObject.put("name", cart.getName());
              jsonObject.put("price", cart.getPrice());
              jsonObject.put("count", cart.getCount() );
              jsonObject.put("type", cart.getOrderType());
              jsonArray.put(jsonObject);
          }

      }catch (Exception e)
      {
          e.printStackTrace();
      }


        return  jsonArray.toString();*/
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Loading");
        pd.setMessage("Confirming your order....  Please wait");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();

        if(s.equals("success"))
        {
            cart.clear();
            Toast.makeText(c,"Order Confirmed Successfully",Toast.LENGTH_LONG).show();
            Intent i=new Intent(c,MenuActivity.class);
            i.putExtra("table",us.getId());
            c.startActivity(i);
          //  Toast.makeText(c,s,Toast.LENGTH_LONG).show();


        }else
        {
            Toast.makeText(c,s,Toast.LENGTH_LONG).show();
        }
    }
}
