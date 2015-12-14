package com.example.sangil.testrecipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by sangil on 2015-12-09.
 */
public class Alarm extends Fragment{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "유통기한 알림", Toast.LENGTH_SHORT)
                .show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_layout, container, false);
        ImageButton back_Button = (ImageButton) view.findViewById(R.id.back_button);
        back_Button.setOnClickListener(mClickListener);
        return view;
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity(), "뒤로가기", Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fl_content, new Tab4());
            ft.addToBackStack(null);
            ft.commit();
        }
    };
}
