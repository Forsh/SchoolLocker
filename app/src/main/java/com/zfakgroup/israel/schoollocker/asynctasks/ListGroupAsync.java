package com.zfakgroup.israel.schoollocker.asynctasks;


import com.example.mac.myapplication.backend.myApi.model.Country;

import java.io.IOException;
import java.util.ArrayList;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//

public class ListGroupAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((AsyncCallback) params[0]);
        try {
            return super.myApiService.listGroups("").execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Country>();
        }
    }
}