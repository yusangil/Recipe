package com.example.sangil.testrecipe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by sangil on 2015-12-09.
 */
public class Recipe extends Fragment{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "RecipeFragment", Toast.LENGTH_SHORT)
                .show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_list, container, false);

        Button list_Button = (Button) view.findViewById(R.id.ok_button);
        list_Button.setOnClickListener(mClickListener);
        return view;
    }

    public Button.OnClickListener mClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ok_button:
                    Toast.makeText(getActivity(), "list_button", Toast.LENGTH_SHORT)
                            .show();
                    button1(v);
                    Fragment newFragment = new Tab1();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fl_content, newFragment);
                    transaction.commit();
                    break;
            }
        }
    };

    public void button1(View v){


        AlertDialog dialog = createDialogBox( v);

        dialog.show();

    }
    private AlertDialog createDialogBox(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("요리 완료");
        builder.setMessage("‘확인’을 누르시면 재료가 감소 됩니다. 그래도 진행 하시겠습니까?");

        // msg 는 그저 String 타입의 변수, tv 는 onCreate 메서드에 글을 뿌려줄 TextView
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){

            }
        });

        AlertDialog dialog = builder.create();
        return dialog;

    }
}
