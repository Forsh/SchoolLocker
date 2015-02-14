package com.zfakgroup.israel.schoollocker.asynctasks;

import com.example.mac.myapplication.backend.myApi.model.Group;
import com.example.mac.myapplication.backend.myApi.model.User;

import java.io.IOException;

/**
 * Created by Sasha on 14.02.15.
 */
public class CreateGroupAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        //AsyncCallback, email, password

        Group toInsert = new Group();
        if (params[1] instanceof Group)
            toInsert = (Group) params[1];
        if (params[0] instanceof AsyncCallback) {
            super.init((AsyncCallback) params[0]);
        }
        try {
            return super.myApiService.createGroup(toInsert).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return new User();
        }
    }
}
