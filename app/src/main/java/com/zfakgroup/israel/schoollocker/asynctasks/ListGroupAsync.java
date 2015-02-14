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


        String request =" ";
        if(params.length >=3  && params[1].toString() == "user" )
            request+=

                    "JOIN " +
                            "USERSINGROUPS " +
                            "ON " +
                            "USERSINGROUPS.GroupId = GROUPS.Id " +
                            "having " +
                            "USERSINGROUPS.UserId = "+ params[2].toString();
        if(params.length >=3 && params[1].toString() =="univ" )
            request+=

                    "JOIN " +
                            "GROUPSINUNIVERSITIES " +
                            "ON " +
                            "GROUPSINUNIVERSITIES.GroupId = GROUPS.Id" +
                            " having " +
                            "GROUPSINUNIVERSITIES.UniversityId = "+ params[2].toString();


        try {
            return super.myApiService.listGroups("").execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Country>();
        }
    }
}