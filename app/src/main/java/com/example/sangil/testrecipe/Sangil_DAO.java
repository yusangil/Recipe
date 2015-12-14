package com.example.sangil.testrecipe;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sangil on 2015-12-11.
 */
public class Sangil_DAO {

    public static void InsertIngredient(Context context,Ingredient ingredient){
        DBHelper dbHelper = new DBHelper(context);
                dbHelper.open().mDB.execSQL("insert into " + Ingredient.TABLE_NAME + " values (null, " + ingredient.ingredient_status + ", '"
                        + ingredient.name + "', " + ingredient.count + ", "+ ingredient.gram + ", '" + ingredient.shelfLifeToString() + "');");
    dbHelper.close();
    }

    public static ArrayList selcectIngredient(Context context){
        DBHelper dbHelper = new DBHelper(context);
        ArrayList<Ingredient> Array_Ingredient = new ArrayList<>();
        Cursor cursor = dbHelper.open().mDB.rawQuery("select * from "+Ingredient.TABLE_NAME+";", null);

        while (cursor.moveToNext()){
            Ingredient tmp = new Ingredient(cursor);
            Log.d("ingredient tmp",""+tmp.name);
            Array_Ingredient.add(tmp);
        }
        cursor.close();
        dbHelper.close();
    return Array_Ingredient;
    }
    public static ArrayList selcectIngredientName(Context context, int status){
        DBHelper dbHelper = new DBHelper(context);
        ArrayList<String> Array_Ingredient = new ArrayList<>();
        Cursor cursor = dbHelper.open().mDB.rawQuery("select " + Ingredient.INGREDIENT_NAME+" from "+Ingredient.TABLE_NAME+" " +
                                                        " where "+ Ingredient.INGREDIENT_STATUS+" = "+status + " ;", null);

        while (cursor.moveToNext()){
            String tmp = new String();
            tmp = cursor.getString(0);
            Log.d("ingredient tmp",""+tmp);
            Array_Ingredient.add(tmp);
        }
        cursor.close();
        dbHelper.close();
        return Array_Ingredient;
    }

    public static void deleteIngredient(Context context, String IngredientName){
        DBHelper dbHelper = new DBHelper(context);
        Cursor cursor = dbHelper.open().mDB.rawQuery("Delete from "+Ingredient.TABLE_NAME + "where" + Ingredient.INGREDIENT_NAME + " = '"+IngredientName+"';", null);
        dbHelper.close();
    }
}

