package com.zfakgroup.israel.schoollocker;


import android.content.Context;

import com.example.mac.myapplication.backend.myApi.model.Country;

import java.io.IOException;
import java.util.ArrayList;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
// params[2] - String GroupId
public class GetCourseAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((AsyncCallback) params[1]);
        try {
            return super.myApiService.getCourse(params[2].toString()).execute();
        } catch (IOException e) {
            return null;
        }
    }
}