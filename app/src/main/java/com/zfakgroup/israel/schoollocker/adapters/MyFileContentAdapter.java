package com.zfakgroup.israel.schoollocker.adapters;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Content;
import com.example.mac.myapplication.backend.myApi.model.Course;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.myfragments.ContentCourseFragment;
import com.zfakgroup.israel.schoollocker.myfragments.FragmentFiles;

/**
 * Created by mac on 13.01.15.
 */
public class MyFileContentAdapter extends ArrayAdapter<Content> {
    Activity activity;
    FragmentFiles fragmentFiles;
    public MyFileContentAdapter(int id, Content[] results, Activity activity, FragmentFiles fragmentFiles) {
        super(activity, id, results);
        this.activity = activity;
        this.fragmentFiles = fragmentFiles;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Content sr = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.file_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.nameSearchResult)).setText(sr.getName());
        ((TextView) convertView.findViewById(R.id.descrSearchResult)).setText(sr.getUrl());
        convertView.findViewById(R.id.buttonADD).setVisibility(View.VISIBLE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(), "FUUUUUU", Toast.LENGTH_LONG).show();
                if (fragmentFiles != null){}
                   //
            }
        });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(getContext(), "LONG", Toast.LENGTH_LONG).show();
                (activity.findViewById(R.id.bottomBarFile)).setVisibility(View.VISIBLE);
                return false;
            }
        });
        return convertView;
    }
}
