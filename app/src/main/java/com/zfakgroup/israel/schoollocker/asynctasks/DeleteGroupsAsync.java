package com.zfakgroup.israel.schoollocker.asynctasks;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sasha on 15.02.15.
 */
public class DeleteGroupsAsync extends EndpointClassAsync {
    @Override
    protected Object doInBackground(Object... params) {
        super.init((AsyncCallback) params[0]);
        try {
            super.myApiService.deleteGroup((ArrayList<Integer>) params[1], 0).execute();
        } catch (IOException e) {
            //return null;
        }
        return new Object();
    }
}
