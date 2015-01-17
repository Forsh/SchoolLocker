package com.zfakgroup.israel.schoollocker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {


    // Обращение к базе данных осуществляется через интерфейс.
    IServiceConnect connect;


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




            fragmentLogin = new FragmentLogIn();
            fragmentSignUp = new FragmentSignUp();
            transaction = getFragmentManager().beginTransaction();
            //анимация при запуске приложения
            transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);

            transaction.replace(R.id.fragment_container, fragmentLogin);
            transaction.addToBackStack(null);
            transaction.commit();



    }




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

        switch(v.getId()){
            //if the signUp button was pressed
           case R.id.buttonSignUp:
            transaction = getFragmentManager().beginTransaction();

            //  transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN); //вид анимации - проявление
            transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right, R.animator.slide_in_left, R.animator.slide_in_right);//вид анимации - наплыв сверху

            if(fragmentLogin.isVisible()){
                transaction.replace(R.id.fragment_container, fragmentSignUp);
            }
            else{
                transaction.replace(R.id.fragment_container, fragmentLogin);
            }
            transaction.addToBackStack("");
            transaction.commit();
                break;

            //if the login button was pressed
            case R.id.buttonLogIn:
                EditText editMail = (EditText)findViewById(R.id.editMail);
                EditText editPassword = (EditText)findViewById(R.id.editPassword);
                connect = new ConnectToLocalDB();
                connect.login(editMail.getText().toString(),editPassword.getText().toString());

                 Log.d("MESSAGE", editMail.getText().toString());
                Log.d("MESSAGE", editPassword.getText().toString());
                break;

            default:
                Log.d("MESSAGE","default");


        }

    }
}
