package com.zfakgroup.israel.schoollocker.asynctasks;


import android.os.AsyncTask;

import com.example.mac.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;

import java.io.IOException;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
public abstract class EndpointClassAsync extends AsyncTask<Object, Void, Object> {
    protected static MyApi myApiService = null;
    protected AsyncCallback asyncCallback;

    @Override
    protected void onPostExecute(Object result) {
        asyncCallback.callback(result);
    }

    protected void init(AsyncCallback asyncCallback) {
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
        this.asyncCallback = asyncCallback;
    }
}