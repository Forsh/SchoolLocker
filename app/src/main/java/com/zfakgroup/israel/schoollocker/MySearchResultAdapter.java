package com.zfakgroup.israel.schoollocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zfakgroup.israel.schoollocker.R;

import java.util.ArrayList;

/**
 * Created by mac on 13.01.15.
 */
public class MySearchResultAdapter extends ArrayAdapter<SearchResult> {
    public MySearchResultAdapter(Context context,int id ,ArrayList<SearchResult> results) {
        super(context, id, results);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchResult sr = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.nameSearchResult)).setText(sr.name);
        ((TextView) convertView.findViewById(R.id.descrSearchResult)).setText(sr.description);
        ((ImageView) convertView.findViewById(R.id.imageSearchResult)).setImageBitmap(sr.image);
        return convertView;
    }
}