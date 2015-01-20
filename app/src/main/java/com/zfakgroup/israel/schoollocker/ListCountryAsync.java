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
public class ListCountryAsync extends AsyncTask<Object, Void, List<Country>> {
    private static MyApi myApiService = null;
    private Context context;
    private AsyncCallback asyncCallback;

    @Override
    protected List<Country> doInBackground(Object... params) {
        init();
        context = (Context) params[0];
        asyncCallback = (AsyncCallback)params[1];
        try {
            return myApiService.listCountries().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Country>();
        }

    }

    @Override
    protected void onPostExecute(List<Country> result) {
        Toast.makeText(context, "ListGroupAsync", Toast.LENGTH_LONG).show();
        asyncCallback.callback(result);
    }

    private void init() {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://golden-tempest-803.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
    }
}