package com.zfakgroup.israel.schoollocker;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Country;
import com.example.mac.myapplication.backend.myApi.model.Course;
import com.example.mac.myapplication.backend.myApi.model.Group;
import com.example.mac.myapplication.backend.myApi.model.User;

import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
// ! Тестирование бекэнда, загруженного на Google App Engine
//https://apis-explorer.appspot.com/apis-explorer/?base=https://golden-tempest-803.appspot.com/_ah/api#p/
//Google Account:
    // testgooglapis@gmail.com
    // LondonIsA123


    // Обращение к базе данных осуществляется через интерфейс.


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] menu;
    private ActionBarDrawerToggle drawerListener;


    public MainActivity() {
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //showActionBar(true);


        menu = getResources().getStringArray(R.array.menu);
        listView = (ListView) findViewById(R.id.drawerList);
        // заполнение ListView значениями из String Array:
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));
        // реализация нажатие элемента в меню:
        listView.setOnItemClickListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drower_open,
                R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this, "Menu Closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this, "Menu Opened", Toast.LENGTH_SHORT).show();
            }
        };

          drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerListener.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle saveInstanceState) {
        super.onPostCreate(saveInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, menu[position] + " was selected", Toast.LENGTH_SHORT).show();
        selectItem(position);

    }

    public void selectItem(int position) {
        listView.setItemChecked(position, true);
        setTitle(menu[position]);
    }


    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    public void showActionBar(boolean sw) {
        if (sw)
            getSupportActionBar().hide();
        else
            getSupportActionBar().show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
        showActionBar(true);
    }
}