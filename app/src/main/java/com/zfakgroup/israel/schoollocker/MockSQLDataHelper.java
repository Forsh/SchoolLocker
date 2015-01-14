package com.zfakgroup.israel.schoollocker;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by mac on 14.01.15.
 * <p/>
 * SQL - helper - заглушка. Пока что будем выполнять запросы к локальной БД.
 */
public class MockSQLDataHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "mySuperDB.db";
    Context mContext;

    public MockSQLDataHelper(Context context, int dbVer) {
        super(context, DB_NAME, null, dbVer);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("mylog", "--- onCreate database ---");
        try {
            StringBuilder buf = new StringBuilder();
            AssetManager assManager = mContext.getAssets();
            InputStream is = null;
            try {
                is = assManager.open("initdb.sql");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            InputStream caInput = new BufferedInputStream(is);
            BufferedReader in =
                    null;
            in = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String str=new String();

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
            str = buf.toString();
            String[] queries = str.split(";");
            for(String query : queries){
                db.execSQL(query);
            }
            Log.v("mylog", str);
        } catch (Exception e) {
            Log.e("mylog", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
