package com.zfakgroup.israel.schoollocker;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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


public class MainActivity extends ActionBarActivity {


    SQLiteDatabase db;
    String LOG_TAG = "Mylog";

    private android.app.Fragment fragmentSignUp;
    private android.app.Fragment fragmentLogin;
    private FragmentTransaction transaction;

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



            fragmentLogin = new FragmentLogIn();
            fragmentSignUp = new FragmentSignUp();
            transaction = getFragmentManager().beginTransaction();
            //анимация при запуске приложения
            transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);

            transaction.replace(R.id.fragment_container, fragmentLogin);
            transaction.addToBackStack(null);
            transaction.commit();



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
    
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View v){
        transaction = getFragmentManager().beginTransaction();

        //  transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN); //вид анимации - проявление
            transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right, R.animator.slide_in_left, R.animator.slide_in_right);//вид анимации - наплыв сверху

        if(fragmentLogin.isVisible()){
            transaction.replace(R.id.fragment_container, fragmentSignUp);
        }else{
            transaction.replace(R.id.fragment_container, fragmentLogin);
        }
        transaction.addToBackStack("");
        transaction.commit();
    }
}
