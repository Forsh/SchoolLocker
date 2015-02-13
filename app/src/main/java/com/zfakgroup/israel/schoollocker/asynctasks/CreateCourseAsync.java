package com.zfakgroup.israel.schoollocker.asynctasks;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.example.mac.myapplication.backend.myApi.model.User;

import java.io.IOException;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
//
public class CreateCourseAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        //AsyncCallback, email, password

        Course toInsert = new Course();
        if (params[1] instanceof Course)
           toInsert = (Course) params[1];
        if (params[0] instanceof AsyncCallback) {
            super.init((AsyncCallback) params[0]);
        }
        try {
            return super.myApiService.createCourse(toInsert).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return new User();
        }
    }
}