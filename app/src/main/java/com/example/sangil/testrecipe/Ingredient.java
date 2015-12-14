package com.example.sangil.testrecipe;

import android.database.Cursor;
import android.util.Log;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yena on 2015-11-13.
 */
public class Ingredient implements Serializable {
    public static final int COLD = 1;
    public static final int ICE = 2;
    public static final int WARM = 3;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);

    public static final String
            TABLE_NAME = "INGREDIENT",
            ID = "ID",
            INGREDIENT_STATUS = "INGREDIENT_STATUS",
            INGREDIENT_NAME = "INGREDIENT_NAME",
            COUNT = "COUNT",
            GRAM = "GRAM",
            SHELF_LIFE="SHELF_LIFE";




    int   count, gram, ingredient_status,id;
    String name;
    Calendar shelfLife;

    Ingredient(){
        shelfLife = Calendar.getInstance();
    }

    Ingredient(Cursor cursor){
        id = cursor.getInt(0);
        ingredient_status = cursor.getInt(1);
        name = cursor.getString(2);
        count = cursor.getInt(3);
        gram = cursor.getInt(4);
        try{
            shelfLife = Calendar.getInstance();
            shelfLife.setTime(sdf.parse(cursor.getString(5)));
        }catch(Exception e){

        }

    }

    String shelfLifeToString(){
        Date date = new Date();
        date.setTime(shelfLife.getTimeInMillis());
        return sdf.format(date);
    }
}

