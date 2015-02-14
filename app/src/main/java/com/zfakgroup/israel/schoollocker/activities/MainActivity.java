package com.zfakgroup.israel.schoollocker.activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.myfragments.ContentCourseFragment;
import com.zfakgroup.israel.schoollocker.myfragments.ContentGroupFragment;
import com.zfakgroup.israel.schoollocker.myfragments.FragmentFiles;
import com.zfakgroup.israel.schoollocker.myfragments.FragmentNewCourse;
import com.zfakgroup.israel.schoollocker.myfragments.FragmentNewGroup;
import com.zfakgroup.israel.schoollocker.myfragments.FragmentSearch;
import com.zfakgroup.israel.schoollocker.myfragments.FragmentSlidingGroupsCourses;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


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
    private int SessionId;
    Fragment fragment;
    private Menu theMenu;
    TextView messageText;
    Button uploadButton;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;
    private String upLoadServerUri = "https://golden-tempest-803.appspot.com/_file/";//"http://10.0.2.2:8080/_file/";

    // МЕГА_КОСТЫЛЬ
    public ContentCourseFragment contentCourseFragment;
    public ContentGroupFragment contentGroupFragment;

    public MainActivity() {
    }

    private int getSavedUserId() {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            fragment = new FragmentSlidingGroupsCourses();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            SessionId = resultCode;
        }
        final String filePath;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getSavedUserId() == 0) {
            startActivityForResult(new Intent(this, LogInActivity.class), 0);
        }

        setContentView(R.layout.activity_log_in);
        fragment = new FragmentSlidingGroupsCourses();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

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
        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
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
        drawerLayout.closeDrawer(Gravity.START);

    }

    public void selectItem(int position) {
        listView.setItemChecked(position, true);
        setTitle(menu[position]);
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        //.remove(fragment)
                        .replace(R.id.fragment_container, new FragmentSlidingGroupsCourses())
                        .addToBackStack("")
                        .commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction()
                        //.remove(fragment)
                        .replace(R.id.fragment_container, new FragmentSearch())
                        .addToBackStack("")
                        .commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction()

                        .replace(R.id.fragment_container, new FragmentNewCourse())
                        .addToBackStack("")
                        .commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction()

                        .replace(R.id.fragment_container, new FragmentNewGroup())
                        .addToBackStack("")
                        .commit();
                break;
        }
    }


    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


//    public void showActionBar(boolean sw) {
//        if (sw)
//            getSupportActionBar().hide();
//        else
//            getSupportActionBar().show();
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.theMenu = menu;
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
        if ((findViewById(R.id.bottomBar) != null && (findViewById(R.id.bottomBar).getVisibility() == View.VISIBLE))) {
            (findViewById(R.id.bottomBar)).setVisibility(View.GONE);
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
        // showActionBar(true);
    }

    public void fragmentWithFiles(int courseId) {
        Bundle bundle = new Bundle();
        bundle.putInt("Id", courseId);
        Fragment toAdd = new FragmentFiles();
        toAdd.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, toAdd)
                .addToBackStack("")
                .commit();
    }

    public interface UploadCallback{
        public void onUploadComplete();
    }

    public int uploadFile(String sourceFileUri, final UploadCallback callback) {


        File f = new File(sourceFileUri);

        final String uploadFileName = f.getName();
        final String uploadFilePath = f.getPath();
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

//            dialog.dismiss();

            Log.e("uploadFile", "Source File not exist :"
                    +uploadFilePath + "" + uploadFileName);

            runOnUiThread(new Runnable() {
                public void run() {
//                    messageText.setText("Source File not exist :"
//                            +uploadFilePath + "" + uploadFileName);
               }
            });

            return 0;

        }
        else
        {
            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", uploadFileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                                + uploadFileName + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                    runOnUiThread(new Runnable() {
                        public void run() {
                            callback.onUploadComplete();
                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                    +" http://www.androidexample.com/media/uploads/"
                                    +uploadFileName;

//                            messageText.setText(msg);
                            Toast.makeText(getApplicationContext(), "File Upload Complete.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

//                dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
//                        messageText.setText("MalformedURLException Exception : check script url.");
                        Toast.makeText(getApplicationContext(), "MalformedURLException",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

//                dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
//                        messageText.setText("Got Exception : see logcat ");
                        Toast.makeText(getApplicationContext(), "Got Exception : see logcat ",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upload file to server Exception", "Exception : "
                        + e.getMessage(), e);
            }
//            dialog.dismiss();
            return serverResponseCode;

        } // End else block
    }
}
