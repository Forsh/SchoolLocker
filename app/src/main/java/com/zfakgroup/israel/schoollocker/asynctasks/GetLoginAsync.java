package com.zfakgroup.israel.schoollocker.asynctasks;

import com.example.mac.myapplication.backend.myApi.model.User;

import java.io.IOException;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
// params[2] - String GroupId
public class GetLoginAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        //AsyncCallback, email, password
        super.init((AsyncCallback) params[0]);

        try {
            return super.myApiService.login((String)params[1],(String)params[2]).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return new User();
        }
    }
}