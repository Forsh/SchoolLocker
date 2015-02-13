package com.zfakgroup.israel.schoollocker.myfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.example.mac.myapplication.backend.myApi.model.University;
import com.zfakgroup.israel.schoollocker.adapters.MyCourseAdapter;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.adapters.UniversityArrayAdapter;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;
import com.zfakgroup.israel.schoollocker.asynctasks.ListCourseAsync;
import com.zfakgroup.israel.schoollocker.asynctasks.ListUniversityAsync;

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
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        view.findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListCourseAsync courses = new ListCourseAsync();
                courses.execute(new GetCoursesAsyncCallback(), "univ", "1");
            }
        });

        ListUniversityAsync listUniversityAsync = new ListUniversityAsync();
        listUniversityAsync.execute(new AsyncCallback() {
            @Override
            public void callback(Object result) {
                ArrayList<University> universities = (ArrayList<University>) result;
                University[] universities1 = new University[universities.size()];
                universities.toArray(universities1);
                ((Spinner)view.findViewById(R.id.UniversityPicker)).setAdapter(new UniversityArrayAdapter(getActivity(), R.layout.simple_list_item_1, universities1));
                for (University u : universities){
                    Toast.makeText(getActivity(), u.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class GetCoursesAsyncCallback implements AsyncCallback{

        @Override
        public void callback(Object result) {
            if (result instanceof List){
                courseResult = new Course[((ArrayList<Course>)result).size()];
                ((ArrayList<Course>) result).toArray(courseResult);
                MyCourseAdapter myAdapter = new MyCourseAdapter(R.layout.search_item, courseResult, getActivity(), null);
                //getView().findViewById(R.id.search_input).setVisibility(View.GONE);
                //getView().findViewById(R.id.listViewSearchResult).getLayoutParams().height = 1700;
                ((ListView)getView().findViewById(R.id.listViewSearchResult)).setAdapter(myAdapter);
            }
        }
    }
}
