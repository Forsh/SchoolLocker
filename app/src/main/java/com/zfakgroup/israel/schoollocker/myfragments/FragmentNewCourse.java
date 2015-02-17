package com.zfakgroup.israel.schoollocker.myfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;
import com.zfakgroup.israel.schoollocker.asynctasks.CreateCourseAsync;

/**
 * Created by mac on 28.01.15.
 */
public class FragmentNewCourse extends Fragment  {
    public FragmentNewCourse() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.buttonCreateCourse).setOnClickListener(new myOnClickListener());
    }

    class myOnClickListener implements View.OnClickListener, AsyncCallback {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Course created", Toast.LENGTH_LONG).show();
            CreateCourseAsync courseAsync = new CreateCourseAsync();
            Course course = new Course();
            String name = ((EditText) getView().findViewById(R.id.inputName)).getText().toString();
            String def = ((EditText) getView().findViewById(R.id.inputDescription)).getText().toString();
            //if (name.length() > 5 && def.length() > 10) {
            course.setName(name);
            course.setDescription(def);
            courseAsync.execute(this, course);
            //}
            getActivity().getSupportFragmentManager().beginTransaction()
                    //.remove(fragment)
                    .replace(R.id.fragment_container, new FragmentSlidingGroupsCourses())
                    .addToBackStack("")
                    .commit();
        }
        @Override
        public void callback(Object result) {
            Toast.makeText(getActivity(), "Course created", Toast.LENGTH_LONG).show();
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_course, container, false);

        return v;
    }


}