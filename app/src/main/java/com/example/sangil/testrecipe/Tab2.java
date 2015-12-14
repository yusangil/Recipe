package com.example.sangil.testrecipe;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by sangil on 2015-12-03.
 */
public class Tab2 extends Fragment  {

    private Ingredient food;
    private EditText name;
    private EditText count;
    private EditText gram;
    private RadioButton cold;
    private RadioButton ice;
    private RadioButton warm;
    private TextView YearOfTextView;
    private TextView MonthOfTextView;
    private TextView DayOfTextView;
    private LinearLayout shelf;
    private RadioGroup radioGroup;
    private ListView lvCold,lvIce,lvWarm;
    ArrayList<String> coldList,iceList,warmList;
    ArrayAdapter<String> coldAdapter,iceAdapter,warmAdapter;

    private void initView(View view) {
        name = (EditText) view.findViewById(R.id.text_id);
        count = (EditText) view.findViewById(R.id.text_num);
        gram = (EditText) view.findViewById(R.id.text_g);
        YearOfTextView = (TextView) view.findViewById(R.id.text_year);
        MonthOfTextView = (TextView) view.findViewById(R.id.text_month);
        DayOfTextView = (TextView) view.findViewById(R.id.text_day);
        shelf = (LinearLayout)view.findViewById(R.id.shelf_life);
        cold = (RadioButton)view.findViewById(R.id.button_cold);
        ice = (RadioButton)view.findViewById(R.id.button_ice);
        warm = (RadioButton)view.findViewById(R.id.button_warm);
        radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        lvCold.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        lvIce.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        lvWarm.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "냉장고", Toast.LENGTH_SHORT)
                .show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlayout_02, container, false);

        lvCold = (ListView)view.findViewById(R.id.List_cold);
        lvIce = (ListView)view.findViewById(R.id.List_ice);
        lvWarm = (ListView)view.findViewById(R.id.List_warm);
        coldList = new ArrayList<String>();
        iceList = new ArrayList<String>();
        warmList = new ArrayList<String>();
        coldAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_single_choice, coldList);
        iceAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_single_choice, iceList);
        warmAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_single_choice, warmList);
        lvCold.setAdapter(coldAdapter);
        lvIce.setAdapter(iceAdapter);
        lvWarm.setAdapter(warmAdapter);
        //createIngredient();

        ImageButton list_Button = (ImageButton) view.findViewById(R.id.list_button);
        ImageButton plus_Button = (ImageButton) view.findViewById(R.id.plus_button);
        list_Button.setOnClickListener(mClickListener);
        plus_Button.setOnClickListener(mClickListener);
        food = new Ingredient();
        return view;
    }

    public Button.OnClickListener mClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.list_button:
                    Toast.makeText(getActivity(), "냉장고DB", Toast.LENGTH_SHORT).show();
                   Fragment newFragment = new Refrigerator_Tab();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fl_content, newFragment);
                    transaction.commit();


                    break;

                case R.id.plus_button:
                    Toast.makeText(getActivity(), "추가하기", Toast.LENGTH_SHORT).show();
                    button(v);


                    break;
            }
        }
    };


    public void button(View v){
      createDialogBox(v);
    }

    private void createDialogBox(View v){

        LayoutInflater inflater = (LayoutInflater)v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate( R.layout.add_menu,null);
        final AlertDialog alert = new AlertDialog.Builder(v.getContext()).create();
        alert.setCancelable(false);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        Button okbutton = (Button)layout.findViewById(R.id.call_button);
        Button canclebutton = (Button)layout.findViewById(R.id.cancel_button);
        initView(layout);

        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                setData();
               // Log.d("food","status : "+food.ingredient_status+" name : "+ food.name+" count : "+ food.count + "gram : "+ food.gram);
                foodCreateIngredient();
                Sangil_DAO.InsertIngredient(v.getContext(), food);

                alert.dismiss();
            }
        });
        canclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "cancel", Toast.LENGTH_SHORT).show();
                alert.cancel();
            }
        });

        shelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog((new ContextThemeWrapper((getActivity()),
                        android.R.style.Theme_Black_NoTitleBar)), listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // TODO Auto-generated method stub
            if (radioGroup.getCheckedRadioButtonId() == R.id.button_cold)
                food.ingredient_status = Ingredient.COLD;
            if (radioGroup.getCheckedRadioButtonId() == R.id.button_ice)
                food.ingredient_status = Ingredient.ICE;
            if (radioGroup.getCheckedRadioButtonId() == R.id.button_warm)
                food.ingredient_status = Ingredient.WARM;
        }});

        alert.setView(layout);
        alert.show();

    }


    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.d("shelfLife", "year : " + year + " month : " + monthOfYear + "day" + dayOfMonth);
            food.shelfLife.set(year, monthOfYear, dayOfMonth);
            YearOfTextView.setText("" + year);
            MonthOfTextView.setText("" + (monthOfYear+1));
            DayOfTextView.setText(""+ dayOfMonth );
        }
    };

    private void setData(){
        food.name = name.getText().toString();
        Log.d("count", count.getText().toString());
        food.count = Integer.parseInt(count.getText().toString());
        food.gram = Integer.parseInt(gram.getText().toString());
    }
    private void createIngredient(){
        coldList.addAll(Sangil_DAO.selcectIngredientName(getContext(), Ingredient.COLD));
        coldAdapter.notifyDataSetChanged();
        iceList.addAll(Sangil_DAO.selcectIngredientName(getContext(), Ingredient.ICE));
        iceAdapter.notifyDataSetChanged();
        warmList.addAll(Sangil_DAO.selcectIngredientName(getContext(), Ingredient.WARM));
        warmAdapter.notifyDataSetChanged();
        }
    private void foodCreateIngredient(){
        switch(food.ingredient_status){
            case Ingredient.COLD:
                coldList.add(food.name);
                coldAdapter.notifyDataSetChanged();
            case Ingredient.ICE:
                iceList.add(food.name);
                iceAdapter.notifyDataSetChanged();
            case Ingredient.WARM:
                warmList.add(food.name);
                warmAdapter.notifyDataSetChanged();
        }
    }
        public void onResume(){
            super.onResume();
            createIngredient();
        }



}
