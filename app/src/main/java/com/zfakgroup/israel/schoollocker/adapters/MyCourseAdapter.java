package com.zfakgroup.israel.schoollocker.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.myfragments.ContentCourseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 13.01.15.
 */
public class MyCourseAdapter extends ArrayAdapter<Course> {
    Activity activity;
    ContentCourseFragment courseFragment;
    public MyCourseAdapter(int id, Course[] results, Activity activity, ContentCourseFragment courseFragment) {
        super(activity, id, results);
        this.activity = activity;
        this.courseFragment = courseFragment;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Course sr = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.nameSearchResult)).setText(sr.getName());
        ((TextView) convertView.findViewById(R.id.descrSearchResult)).setText(sr.getDescription());
        convertView.findViewById(R.id.checkBoxSearchItem).setVisibility(View.VISIBLE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "FUUUUUU", Toast.LENGTH_LONG).show();
                if (courseFragment != null)
                    courseFragment.showCourseContent(position);
            }
        });


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(getContext(), "LONG", Toast.LENGTH_LONG).show();
                (activity.findViewById(R.id.bottomBar)).setVisibility(View.VISIBLE);

                return false;
            }
        });
        return convertView;
    }
}
