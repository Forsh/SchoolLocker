package com.zfakgroup.israel.schoollocker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zfakgroup.israel.schoollocker.R;

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
    public Bitmap image;
    public String name;
    public String description;
    private Date begin, end;


    public SearchResult(String name, String description, String imageURL, Context context){
        this.name=name;
        this.description = description;
        this.image= getBitmapFromURL(imageURL);
    }

    public SearchResult(String name, String description, Context context){
        this.name=name;
        this.description = description;
        this.image= BitmapFactory.decodeResource(context.getResources(), R.drawable.graduate);
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
}
