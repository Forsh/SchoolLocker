package com.zfakgroup.israel.schoollocker.myfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mac.myapplication.backend.myApi.model.Group;
import com.zfakgroup.israel.schoollocker.R;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;
import com.zfakgroup.israel.schoollocker.asynctasks.CreateGroupAsync;

/**
 * Created by Sasha on 14.02.15.
 */
public class FragmentNewGroup extends Fragment {

    public FragmentNewGroup() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.buttonCreateGroup).setOnClickListener(new myOnClickListener());
    }

    class myOnClickListener implements View.OnClickListener, AsyncCallback {
        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), "Group created", Toast.LENGTH_LONG).show();
            CreateGroupAsync groupAsync = new CreateGroupAsync();
            Group group = new Group();
            String name = ((EditText) getView().findViewById(R.id.inputGroupName)).getText().toString();
            String def = ((EditText) getView().findViewById(R.id.inputGroupDescription)).getText().toString();
            //if (name.length() > 5 && def.length() > 10) {
            group.setName(name);
            group.setDescription(def);
            groupAsync.execute(this, group);
            //}
        }
        @Override
        public void callback(Object result) {
            Toast.makeText(getActivity(), "Group created", Toast.LENGTH_LONG).show();
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_group, container, false);

        return v;
    }


}
