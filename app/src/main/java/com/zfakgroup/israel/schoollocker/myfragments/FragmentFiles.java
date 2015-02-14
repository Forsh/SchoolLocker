package com.zfakgroup.israel.schoollocker.myfragments;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Content;
import com.example.mac.myapplication.backend.myApi.model.Course;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.activities.MainActivity;
import com.zfakgroup.israel.schoollocker.adapters.MyCourseAdapter;
import com.zfakgroup.israel.schoollocker.adapters.MyFileContentAdapter;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;
import com.zfakgroup.israel.schoollocker.asynctasks.ListContentsAsync;
import com.zfakgroup.israel.schoollocker.asynctasks.ListCourseAsync;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.com.daidalos.afiledialog.FileChooserActivity;

/**
 * Created by mac on 10.02.15.
 */
public class FragmentFiles extends Fragment {
    int idCourse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        idCourse = args.getInt("Id", 0);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode != Activity.RESULT_CANCELED){
            File file = (File) data.getExtras().get(FileChooserActivity.OUTPUT_FILE_OBJECT);
            final String filePath = file.getAbsolutePath();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity)getActivity()).uploadFile(filePath, new MainActivity.UploadCallback() {
                        @Override
                        public void onUploadComplete() {
                            fill();
                        }
                    });
                }
            }).start();
            Toast.makeText(getActivity(), filePath, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem.OnMenuItemClickListener addListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(getActivity(), FileChooserActivity.class);
                startActivityForResult(intent, 10);
                Toast.makeText(getActivity(), "Adding selected", Toast.LENGTH_LONG).show();
                return true;
            }
        };
        menu.add("Add").setOnMenuItemClickListener(addListener)
                //.setActionView(R.layout.menu_add)
                .setIcon(android.R.drawable.ic_menu_upload)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        MenuItem.OnMenuItemClickListener downloadListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //Toast.makeText(getApplicationContext(), "Adding selected", Toast.LENGTH_LONG).show();

                return true;
            }
        };

        menu.add("Download").setOnMenuItemClickListener(downloadListener)
                //.setActionView(R.layout.menu_add)
                .setIcon(android.R.drawable.stat_sys_download)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.pager_item, container, false);
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fill();
    }

    @Override
    public void onResume() {
        super.onResume();
        fill();
    }

    private void fill(){
        ListContentsAsync listContentsAsync = new ListContentsAsync();
        final ListView contentsListView = (ListView) getView().findViewById(R.id.fullscreenList);
        listContentsAsync.execute(new AsyncCallback() {
            @Override
            public void callback(Object result) {
                if (result instanceof List) {
                    ArrayList<Course> arrayCourses = (ArrayList<Course>) result;
                    Content[] contents = new Content[((ArrayList<Course>) result).size()];
                    arrayCourses.toArray(contents);
                    (contentsListView).setAdapter(new MyFileContentAdapter(R.layout.search_item, contents, getActivity(), FragmentFiles.this));
                }
            }
        });
    }
}
