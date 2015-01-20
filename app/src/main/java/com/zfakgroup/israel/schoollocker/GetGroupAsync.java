package com.zfakgroup.israel.schoollocker;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.MyApi;
import com.example.mac.myapplication.backend.myApi.model.Group;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
public class GetGroupAsync extends AsyncTask<Object, Void, Group> {
    private static MyApi myApiService = null;
    private Context context;
    private AsyncCallback asyncCallback;

    //(Context, Query, ASyncCallback)
    @Override
    protected Group doInBackground(Object... params) {
        init();
        context = (Context) params[0];
        asyncCallback = (AsyncCallback)params[2];
        try {
            return myApiService.getGroup(params[1].toString()).execute();
        } catch (IOException e) {
            return new Group().setDescription(e.toString());
        }

    }

    @Override
    protected void onPostExecute(Group result) {
        Toast.makeText(context, "GetGroupAsync", Toast.LENGTH_LONG).show();
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