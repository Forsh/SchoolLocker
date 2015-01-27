package com.zfakgroup.israel.schoollocker;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.User;


public class LogInActivity extends FragmentActivity {
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(this, "Back pressed", Toast.LENGTH_LONG).show();
    }

    public class LogInListener implements AsyncCallback {

        @Override
        public void callback(Object result) {
            if (result instanceof User) {
                Toast.makeText(getApplicationContext(), "Logged in user № " + ((User) result).getId().toString(), Toast.LENGTH_LONG).show();
                SessionId = ((User) result).getId();
            }
        }
    }

    private FragmentTransaction transaction;
    int SessionId;
    IServiceConnect connect;
    Fragment fragmentLogin;
    Fragment fragmentSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen);

        connect = new GoogleServiceConnect();
        connect.setListener("login", new LogInActivity.LogInListener());

        // Главный экран - контейнер фрагментов во весь экран
        setContentView(R.layout.fullscreen);


        fragmentLogin = new FragmentLogIn();
        fragmentSignUp = new FragmentSignUp();
        transaction = getFragmentManager().beginTransaction();
        //анимация при запуске приложения
        transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);

        transaction.replace(R.id.fragment_container, fragmentLogin);
        //transaction.addToBackStack(null);
        transaction.commit();


    }



    public void onClick(View v) {

        switch (v.getId()) {
            //if the signUp button was pressed
            case R.id.buttonSignUp:
                transaction = getFragmentManager().beginTransaction();

                //  transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN); //вид анимации - проявление
                transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right,
                        R.animator.slide_in_left, R.animator.slide_in_right);//вид анимации - наплыв сверху

                if (fragmentLogin.isVisible()) {
                    transaction.replace(R.id.fragment_container, fragmentSignUp);
                } else {
                    transaction.replace(R.id.fragment_container, fragmentLogin);
                }
                transaction.addToBackStack("");
                transaction.commit();
                break;

            //if the login button was pressed
            case R.id.buttonLogIn:
                EditText editMail = (EditText) findViewById(R.id.editMail);
                EditText editPassword = (EditText) findViewById(R.id.editPassword);
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.logInProgressBar);
                progressBar.setVisibility(View.VISIBLE);
                findViewById(R.id.buttonSignUp).setVisibility(View.GONE);

                connect.login(editMail.getText().toString(), editPassword.getText().toString());

                Log.d("MESSAGE", editMail.getText().toString());
                Log.d("MESSAGE", editPassword.getText().toString());
                break;

            default:
                Log.d("MESSAGE", "default");
        }
    }
}
