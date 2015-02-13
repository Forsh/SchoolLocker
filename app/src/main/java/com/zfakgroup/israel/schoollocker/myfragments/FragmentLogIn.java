package com.zfakgroup.israel.schoollocker.myfragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zfakgroup.israel.schoollocker.R;

/**
 * Created by mac on 14.01.15.
 */
public class FragmentLogIn extends Fragment {
    public View view;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        TextView tv=(TextView)view.findViewById(R.id.appName);
        Typeface face=Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Roboto-Regular.ttf");

        tv.setTypeface(face);


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Установка обработчика кнопок
        //((Button) view.findViewById(R.id.buttonLogIn)).setOnClickListener(this);
        //((Button) view.findViewById(R.id.buttonSignUp)).setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.buttonLogIn:
//                // --- Осуществить LOG IN
//                break;
//            case R.id.buttonSignUp:
//                // Перейти к регистрации
//                break;
//        }
//    }
}
