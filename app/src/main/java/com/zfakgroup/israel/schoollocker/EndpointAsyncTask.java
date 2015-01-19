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

class EndpointsAsyncTask extends AsyncTask<Pair<Integer, Context>, Void, Group> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected Group doInBackground(Pair<Integer, Context>... params) {
        context = params[0].second;
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://golden-tempest-803.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();

        }


        try {
            return myApiService.getGroup("1").execute();
        } catch (IOException e) {
            return new Group().setDescription(e.toString());
        }
    }

    @Override
    protected void onPostExecute(Group result) {
        Toast.makeText(context, result.getDescription(), Toast.LENGTH_LONG).show();
    }
}