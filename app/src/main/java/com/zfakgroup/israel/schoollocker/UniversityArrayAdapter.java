package com.zfakgroup.israel.schoollocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.mac.myapplication.backend.myApi.model.University;

import java.util.ArrayList;

/**
 * Created by mac on 26.01.15.
 */
public class UniversityArrayAdapter extends ArrayAdapter<University> implements SpinnerAdapter {

    public UniversityArrayAdapter(Context context, int resource, University[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        University sr = (University)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1,parent, false);
        ((TextView)view.findViewById(R.id.text1)).setText(sr.getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        University sr = (University)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1,parent, false);
        ((TextView)view.findViewById(R.id.text1)).setText(sr.getName());
        return view;
    }
}
