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
import android.widget.Checkable;
import android.widget.ListView;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.zfakgroup.israel.schoollocker.activities.MainActivity;
import com.zfakgroup.israel.schoollocker.adapters.MyCourseAdapter;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;
import com.zfakgroup.israel.schoollocker.asynctasks.DeleteCoursesAsync;
import com.zfakgroup.israel.schoollocker.asynctasks.ListCourseAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Fragment used to display some meaningful content for each page in the sample's
 * {@link android.support.v4.view.ViewPager}.
 */
public class ContentCourseFragment extends Fragment implements AsyncCallback {

    private static final String KEY_TITLE = "title";
    private static final String KEY_INDICATOR_COLOR = "indicator_color";
    private static final String KEY_DIVIDER_COLOR = "divider_color";
    private Course[] courses;
    ContentCourseFragment me = this;
    ListView courseListView;

    /**
     * @return a new instance of {@link ContentCourseFragment}, adding the parameters into a bundle and
     * setting them as arguments.
     */
    public static ContentCourseFragment newInstance(CharSequence title) {
        Bundle bundle = new Bundle();
        ContentCourseFragment fragment = new ContentCourseFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public void showCourseContent(int course) {
        ((MainActivity) getActivity()).fragmentWithFiles(course);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).contentCourseFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_item, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillListViewCourse();
    }

    public void fillListViewCourse () {
        courseListView = (ListView) getView().findViewById(R.id.fullscreenList);

        ListCourseAsync listCourseAsync = new ListCourseAsync();
        listCourseAsync.execute(new AsyncCallback() {
            @Override
            public void callback(Object result) {
                if (result instanceof List) {
                    ArrayList<Course> arrayCourses = (ArrayList<Course>) result;
                    courses = new Course[((ArrayList<Course>) result).size()];
                    arrayCourses.toArray(courses);
                    (courseListView).setAdapter(new MyCourseAdapter(R.layout.search_item, courses, getActivity(), me));
                }
            }
        });
    }

    public void deleteSelected() {
        ArrayList<Integer> idToDelete = new ArrayList<>();
        for (int pos = 0; pos < courseListView.getChildCount(); pos++) {
            if (((CheckBox) courseListView.getChildAt(pos).findViewById(R.id.checkBoxSearchItem)).isChecked()) {
                idToDelete.add(courses[pos].getId());
            }

        }
        DeleteCoursesAsync dca = new DeleteCoursesAsync();
        dca.execute(this, idToDelete);
        fillListViewCourse();
    }

    @Override
    public void callback(Object result) {

    }
}
