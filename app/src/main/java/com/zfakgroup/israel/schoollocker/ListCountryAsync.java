package com.zfakgroup.israel.schoollocker;


import android.content.Context;
import com.example.mac.myapplication.backend.myApi.model.Country;

import java.io.IOException;
import java.util.ArrayList;

// Доступ к сетевым ресурсам (сервису, базе данных) должен выполняться в отдельном
// потоке. После выполнения запроса поток вызовет функцию  onPostExecute.
//
public class ListCountryAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((Context)params[0], (AsyncCallback)params[1]);
        try {
            return super.myApiService.listCountries().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Country>();
        }

    }
}