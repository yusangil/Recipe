package com.example.sangil.testrecipe;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sangil on 2015-12-10.
 */
public class DBHelper{

    private Context cnt;
    private Helper mHelper;
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="refrigerator.db";
    public static SQLiteDatabase mDB;

    private class Helper extends SQLiteOpenHelper{

        public Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table "+FoodRecipe.TABLE_NAME+"("
                    +FoodRecipe.ID+" integer primary key autoincrement, " //0
                    +FoodRecipe.PICTURE+" Blob,"                              //1
                    +FoodRecipe.RECIPE_NAME+" text not null , "             //2
                    +FoodRecipe.MAIN_INGREIENT+" text not null , "          //3
                    +FoodRecipe.SUB_INGREIENT+" text  not null, "           //4
                    +FoodRecipe.HOW_RECIPE+" text not null, "                //5
                    +FoodRecipe.STAR+" int ); ");                              //6

            db.execSQL("create table "
                    + Ingredient.TABLE_NAME + "("
                    + Ingredient.ID + " integer primary key autoincrement, " //0
                    + Ingredient.INGREDIENT_STATUS + " int , "                              //1
                    + Ingredient.INGREDIENT_NAME + " text not null , "             //2
                    + Ingredient.COUNT + " int , "          //3
                    + Ingredient.GRAM + " int , "           //4
                    + Ingredient.SHELF_LIFE + " text ); ");                              //5
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


    public DBHelper(Context context) {
       this.cnt = context;
    }


    public DBHelper open() throws SQLException {
        mHelper = new Helper(cnt, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }
}
