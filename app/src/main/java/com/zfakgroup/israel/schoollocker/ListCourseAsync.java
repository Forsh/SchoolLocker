package com.zfakgroup.israel.schoollocker;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.MyApi;
import com.example.mac.myapplication.backend.myApi.model.Country;
import com.example.mac.myapplication.backend.myApi.model.Course;
import com.example.mac.myapplication.backend.myApi.model.Group;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
public class ListCourseAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((AsyncCallback) params[0]);
        String request =" ";
        if(params[1] != null && params[2] != null && params[1].toString() =="user" )
            request+=

                    "JOIN " +
                            "USERSINCOURSES " +
                            "ON " +
                            "USERSINCOURSES.CourseId = COURSES.Id " +
                            "having " +
                            "USERSINCOURSES.UserId = "+ params[2].toString();
        if(params[1] != null && params[2] != null && params[1].toString() =="univ" )
            request+=

                    "JOIN " +
                            "COURSESINUNIVERSITIES " +
                            "ON " +
                            "COURSESINUNIVERSITIES.CourseId = COURSES.Id" +
                            " having " +
                            "COURSESINUNIVERSITIES.UniversityId = "+ params[2].toString();
        try {
            return super.myApiService.listCourses(request).execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Country>();
        }
    }
}
