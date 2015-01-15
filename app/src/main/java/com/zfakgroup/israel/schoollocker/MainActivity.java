package com.zfakgroup.israel.schoollocker;

import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends FragmentActivity

{
    SQLiteDatabase db;
    String LOG_TAG = "Mylog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Главный экран - контейнер фрагментов во весь экран
        setContentView(R.layout.fullscreen);



        //Interface for interacting with Fragment objects inside of an Activity
        //Добавляет и убирает фрагменты с Activity
       FragmentManager fragmentManager = getFragmentManager();
       fragmentManager.beginTransaction().add(R.id.fragment_container,new FragmentLogIn()).commit();
        // Обращение к базе данных осуществляется через интерфейс.
        IServiceConnect connect;


    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
