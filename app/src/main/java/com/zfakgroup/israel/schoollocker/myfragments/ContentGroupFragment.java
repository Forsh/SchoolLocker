package com.zfakgroup.israel.schoollocker.myfragments;/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.mac.myapplication.backend.myApi.model.Group;
import com.zfakgroup.israel.schoollocker.activities.MainActivity;
import com.zfakgroup.israel.schoollocker.adapters.MyCourseAdapter;
import com.zfakgroup.israel.schoollocker.adapters.MyGroupsAdapter;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;
import com.zfakgroup.israel.schoollocker.asynctasks.DeleteCoursesAsync;
import com.zfakgroup.israel.schoollocker.asynctasks.DeleteGroupsAsync;
import com.zfakgroup.israel.schoollocker.asynctasks.ListGroupAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Fragment used to display some meaningful content for each page in the sample's
 * {@link android.support.v4.view.ViewPager}.
 */
public class ContentGroupFragment extends Fragment implements AsyncCallback {

    private static final String KEY_TITLE = "title";
    private static final String KEY_INDICATOR_COLOR = "indicator_color";
    private static final String KEY_DIVIDER_COLOR = "divider_color";
    private Group[] groups;
    ContentGroupFragment me = this;
    ListView groupListView;

    /**
     * @return a new instance of {@link ContentGroupFragment}, adding the parameters into a bundle and
     * setting them as arguments.
     */
    public static ContentGroupFragment newInstance(CharSequence title) {
        Bundle bundle = new Bundle();
        ContentGroupFragment fragment = new ContentGroupFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public void showGroupContent(int group) {
        ((MainActivity) getActivity()).fragmentWithFiles(group);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).contentGroupFragment = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_item, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillListViewGroup();

       // Bundle args = getArguments();
//
//        if (args != null) {
//            TextView title = (TextView) view.findViewById(R.id.item_title);
//            title.setText("Title: " + args.getCharSequence(KEY_TITLE));
//
//            int indicatorColor = args.getInt(KEY_INDICATOR_COLOR);
//            TextView indicatorColorView = (TextView) view.findViewById(R.id.item_indicator_color);
//            indicatorColorView.setText("Indicator: #" + Integer.toHexString(indicatorColor));
//            indicatorColorView.setTextColor(indicatorColor);
//
//            int dividerColor = args.getInt(KEY_DIVIDER_COLOR);
//            TextView dividerColorView = (TextView) view.findViewById(R.id.item_divider_color);
//            dividerColorView.setText("Divider: #" + Integer.toHexString(dividerColor));
//            dividerColorView.setTextColor(dividerColor);
//        }
    }

    public void fillListViewGroup() {
        groupListView = (ListView) getView().findViewById(R.id.fullscreenList);


        ListGroupAsync listGroupAsync = new ListGroupAsync();
        listGroupAsync.execute(new AsyncCallback() {
            @Override
            public void callback(Object result) {
                if (result instanceof List) {
                    ArrayList<Group> arrayGroups = (ArrayList<Group>) result;
                    groups = new Group[((ArrayList<Group>) result).size()];
                    arrayGroups.toArray(groups);
                    (groupListView).setAdapter(new MyGroupsAdapter(R.layout.search_item, groups, getActivity(), me));
                    //((ListView) view.findViewById(R.id.fullscreenList)).setAdapter(new MyGroupsAdapter(getActivity(), R.layout.search_item, groups));
                }
            }
        });
    }

    public void deleteSelected() {
        ArrayList<Integer> idToDelete = new ArrayList<>();
        for (int pos = 0; pos < groupListView.getChildCount(); pos++) {
            if (((CheckBox) groupListView.getChildAt(pos).findViewById(R.id.checkBoxSearchItem)).isChecked()) {
                idToDelete.add(groups[pos].getId());
            }
        }
        DeleteGroupsAsync dga = new DeleteGroupsAsync();
        dga.execute(this, idToDelete);
        fillListViewGroup();
    }


    @Override
    public void callback(Object result) {

    }
}
