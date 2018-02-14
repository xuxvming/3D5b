package com.ramotion.expandingcollection.examples.simple;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.support.annotation.RequiresApi;
import android.util.EventLogTags;
import android.util.JsonReader;
import android.util.Xml;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import javax.net.ssl.HttpsURLConnection;

/**
 * Created by oliver on 2018/2/8.
 */

public class News {
    private String id;
    private String author;
    private String title;
    private String description;
    private String URL;
    private String image;
    private String publishedAt;

    private  List<String> newslist;

    public News(String id, String author, String title, String description, String URL, String image, String publishedAt) {
        this.id = id;
        this.author = author;
        this.description = title;
        this.description = description;
        this.URL = URL;
        this.image = image;
        this.publishedAt = publishedAt;
        newslist = null;


    }
    public News(){
        this.id = id;
        this.author = author;
        this.description = title;
        this.description = description;
        this.URL = URL;
        this.image = image;
        this.publishedAt = publishedAt;
        newslist = null;
    }


    public List<String> getNewslist(){
        return newslist;
    }

    public String getTitle(){
        return title;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void getNews() throws IOException {
      //  String str = "";
        URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=17d5ac208dc844ffabe44a2d87e1d3f0");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(15001);
        conn.setConnectTimeout(15001);
        conn.setRequestMethod("GET");

        InputStream stream = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuffer stringbuffer = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {

            try {
                JSONObject dataJson = new JSONObject(line);
                String id = (String) dataJson.get("id");
                String author = (String) dataJson.get("author");
                String title = (String) dataJson.get("title");
                String description = dataJson.getString("description");
                String URL = dataJson.getString("url");
                String image = dataJson.getString("image");
                String publishedAt = dataJson.getString("publishedAt");

                News news = new News(id, author, title, description, URL, image, publishedAt);

               news.getNewslist().add(description);
                System.out.println(author);
                //description,URL,image,publishedAt);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public static Drawable LoadImage(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "head");
            return d ;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}
