package com.example.sangil.testrecipe;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sangil on 2015-12-09.
 */
public class Refrigerator_Tab extends Fragment {

   //private ArrayList<Ingredient> allList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this.getContext(), "뒤로가기", Toast.LENGTH_SHORT).show();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refrigerator_list, container, false);
        ImageButton back_Button = (ImageButton) view.findViewById(R.id.back_button);
        back_Button.setOnClickListener(mClickListener);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_completed);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Ingredient> items = new ArrayList<>();
        items.clear();
        items.addAll(Sangil_DAO.selcectIngredient(getContext()));
        recyclerView.setAdapter(new RecyclerViewAdapter(getContext(), items, R.layout.refrigerator_list));
        return view;
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "뒤로가기", Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fl_content, new Tab2());
            ft.addToBackStack(null);
            ft.commit();

        }
    };

}

