package com.zfakgroup.israel.schoollocker;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Group;

import java.util.List;


public class MainActivity extends ActionBarActivity implements AsyncCallback{
 {
// ! Тестирование бекэнда, загруженного на Google App Engine
//https://apis-explorer.appspot.com/apis-explorer/?base=https://golden-tempest-803.appspot.com/_ah/api#p/
//Google Account:
    // testgooglapis@gmail.com
    // LondonIsA123

    private DrawerLayout drawerLayout;
    private ListView listView;
    private View toolbar;


    // Обращение к базе данных осуществляется через интерфейс.
    IServiceConnect connect;

    private android.app.Fragment fragmentSignUp;
    private android.app.Fragment fragmentLogin;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        listView=(ListView) findViewById(R.id.drawerList);
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

        GetGroupAsync asyncTask = new GetGroupAsync();
        //asyncTask.execute(this,"1",this);
        ListGroupAsync listGroupAsync = new ListGroupAsync();
        listGroupAsync.execute(this,this);
    
     toolbar =  findViewById(R.id.app_bar);
     setSupportActionBar((Toolbar) toolbar);
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
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {
            //if the signUp button was pressed
            case R.id.buttonSignUp:
                transaction = getFragmentManager().beginTransaction();

                //  transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN); //вид анимации - проявление
                transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right, R.animator.slide_in_left, R.animator.slide_in_right);//вид анимации - наплыв сверху

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
//                connect = new ConnectToLocalDB();
//                connect.login(editMail.getText().toString(), editPassword.getText().toString());

                Log.d("MESSAGE", editMail.getText().toString());
                Log.d("MESSAGE", editPassword.getText().toString());
                break;

            default:
                Log.d("MESSAGE", "default");
        }
    }

    @Override
    public void callback(Object result) {
        for(Group group : ((List<Group>)result)){
            Toast.makeText(this, group.getName(),Toast.LENGTH_LONG).show();
        }
    }
}
