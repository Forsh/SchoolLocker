package com.zfakgroup.israel.schoollocker;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.MyApi;
import com.example.mac.myapplication.backend.myApi.model.Country;
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

public class ListGroupAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((AsyncCallback) params[1]);
        try {
            return super.myApiService.listGroups("").execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Country>();
        }
    }
}