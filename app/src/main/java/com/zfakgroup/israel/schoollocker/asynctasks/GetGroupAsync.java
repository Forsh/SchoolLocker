package com.zfakgroup.israel.schoollocker.asynctasks;


import java.io.IOException;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
// params[2] - String GroupId
public class GetGroupAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((AsyncCallback) params[1]);
        try {
            return super.myApiService.getGroup(params[2].toString()).execute();
        } catch (IOException e) {
            return null;
        }

    }
}