package com.zfakgroup.israel.schoollocker.asynctasks;


import com.example.mac.myapplication.backend.myApi.model.Country;

import java.io.IOException;
import java.util.ArrayList;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
public class ListContentsAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((AsyncCallback) params[0]);
        String request =" ";
        if(params.length >=3  && params[1].toString() == "user" )
            request+=

                    "JOIN " +
                            "USERSINCOURSES " +
                            "ON " +
                            "USERSINCOURSES.CourseId = COURSES.Id " +
                            "having " +
                            "USERSINCOURSES.UserId = "+ params[2].toString();
        if(params.length >=3 && params[1].toString() =="univ" )
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
