package com.zfakgroup.israel.schoollocker.asynctasks;


import java.io.IOException;
import java.util.ArrayList;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
// params[2] - String GroupId
public class DeleteCoursesAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((AsyncCallback) params[0]);
        try {
            super.myApiService.deleteCourse((ArrayList<Integer>) params[1], 0).execute();
        } catch (IOException e) {
            //return null;
        }
        return new Object();
    }
}