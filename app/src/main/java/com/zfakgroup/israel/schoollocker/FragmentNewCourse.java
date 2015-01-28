package com.zfakgroup.israel.schoollocker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Course;

/**
 * Created by mac on 28.01.15.
 */
public class FragmentNewCourse extends Fragment {
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

        ((Button) view.findViewById(R.id.buttonCreateCourse)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateCourseAsync courseAsync = new CreateCourseAsync();
                Course course = new Course();
                String name = ((EditText) view.findViewById(R.id.inputName)).getText().toString();
                String def = ((EditText) view.findViewById(R.id.inputDescription)).getText().toString();
                if (name.length() > 5 && def.length() > 10) {
                    course.setName(name);
                    course.setDescription(def);
                    courseAsync.execute(
                            new AsyncCallback() {
                                @Override
                                public void callback(Object result) {
                                    Toast.makeText(getActivity(), "Course created", Toast.LENGTH_LONG).show();
                                }
                            }
                            , course);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_course, container, false);

        return v;
    }
}