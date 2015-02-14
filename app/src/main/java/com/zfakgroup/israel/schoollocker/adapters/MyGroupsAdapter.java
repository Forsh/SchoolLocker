package com.zfakgroup.israel.schoollocker.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.example.mac.myapplication.backend.myApi.model.Group;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.myfragments.ContentCourseFragment;
import com.zfakgroup.israel.schoollocker.myfragments.ContentGroupFragment;

/**
 * Created by mac on 13.01.15.
 */
public class MyGroupsAdapter extends ArrayAdapter<Group> {
    Activity activity;
    ContentGroupFragment groupFragment;

    public MyGroupsAdapter(int id, Group[] results, Activity activity, ContentGroupFragment groupFragment) {
        super(activity, id, results);
        this.activity = activity;
        this.groupFragment = groupFragment;
    }
    /*
    public MyGroupsAdapter(Context context, int id, Group[] results) {
        super(context, id, results);
    }
*/
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Group sr = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_item, parent, false);
        }


        ((TextView) convertView.findViewById(R.id.nameSearchResult)).setText(sr.getName());
        ((TextView) convertView.findViewById(R.id.descrSearchResult)).setText(sr.getDescription());
        convertView.findViewById(R.id.buttonADD).setVisibility(View.VISIBLE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "FUUUUUU", Toast.LENGTH_LONG).show();
                if (groupFragment != null)
                    groupFragment.showGroupContent(position);
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(), "LONG", Toast.LENGTH_LONG).show();
                (activity.findViewById(R.id.bottomBar)).setVisibility(View.VISIBLE);

                return false;
            }
        });


        return convertView;
    }
}
