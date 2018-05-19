package com.grafixartist.gallery;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Fetch extends AsyncTask<Void, Void, List<String>> {
    private String data="";
    private String da="";
    private String full="";
    private String ku="";
    List<String> UrlList=new ArrayList<>();
    @Override
    protected void onPostExecute(List<String> ois) {
    }
    @Override
    protected List<String> doInBackground(Void... voids) {
        try {
            URL a=new URL("http://mondaymorning.nitrkl.ac.in/api/post/get/featured");
            HttpURLConnection b=(HttpURLConnection) a.openConnection();
            InputStream i=b.getInputStream();
            BufferedReader h=new BufferedReader(new InputStreamReader(i));
            String l="";
            while(l!=null){
                l=h.readLine();
                data=data+l;
            }
            JSONObject JA=new JSONObject(data);
            JSONObject k=JA.getJSONObject("slider");
            full=k.getString("imageUrlPrefix");
            JSONArray f=k.getJSONArray("posts");
            JSONObject q=f.getJSONObject(0);
            da=q.getString("featured_image");
            full=full+da;
            UrlList.add(full);
            k=JA.getJSONObject("top4");
            String Image=k.getString("imageUrlPrefix");
            f=k.getJSONArray("posts");
            for(int g=0;g<f.length();g++)
            {
                q=f.getJSONObject(g);
                da=q.getString("featured_image");
                UrlList.add(Image+da);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return UrlList;
    }
}

