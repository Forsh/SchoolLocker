package com.zfakgroup.israel.schoollocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.backend.myApi.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 13.01.15.
 */
public class MySearchResultAdapter extends ArrayAdapter<Course> {
    public MySearchResultAdapter(Context context, int id , Course[] results) {
        super(context, id, results);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course sr = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.nameSearchResult)).setText(sr.getName());
        ((TextView) convertView.findViewById(R.id.descrSearchResult)).setText(sr.getDescription());
        return convertView;
    }
}
