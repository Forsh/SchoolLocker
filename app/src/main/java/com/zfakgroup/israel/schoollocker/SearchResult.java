package com.zfakgroup.israel.schoollocker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by mac on 13.01.15.
 *
 * Объекты этого класса будут создаваться на фрагменте search_fragment.xml из результатов поиска.
 * Они будут заполнять ListView с результатами поиска. Это могут быть группы или курсы.
 *
 */
public class SearchResult {
    private Bitmap image;
    private String name;
    private String description;
    private Date begin;
    private Date end;

    public SearchResult(){

    }

    public SearchResult(String name, String description, String imageURL, Context context){
        this.setName(name);
        this.setDescription(description);
        this.setImage(getBitmapFromURL(imageURL));
    }

    public SearchResult(String name, String description, Context context){
        this.setName(name);
        this.setDescription(description);
        this.setImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.graduate));
    }

    // Этот метод наверно должен быть не здесь))
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
