package com.zfakgroup.israel.schoollocker;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mac.myapplication.backend.myApi.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 14.01.15.
 */
public class FragmentSearch extends Fragment {
    Course[] courseResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        view.findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListCourseAsync courses = new ListCourseAsync();
                courses.execute(new GetCoursesAsyncCallback(), "univ", "1");
            }
        });
        return view;
    }

    public class GetCoursesAsyncCallback implements AsyncCallback{

        @Override
        public void callback(Object result) {
            if (result instanceof List){
                courseResult = new Course[((ArrayList<Course>)result).size()];
                ((ArrayList<Course>) result).toArray(courseResult);
                MySearchResultAdapter myAdapter = new MySearchResultAdapter(getActivity(), R.layout.search_item, courseResult);
                getView().findViewById(R.id.search_input).setVisibility(View.GONE);
                getView().findViewById(R.id.listViewSearchResult).getLayoutParams().height = 1700;
                ((ListView)getView().findViewById(R.id.listViewSearchResult)).setAdapter(myAdapter);
            }
        }
    }
}
