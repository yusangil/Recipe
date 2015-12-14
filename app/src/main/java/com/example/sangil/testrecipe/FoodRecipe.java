package com.example.sangil.testrecipe;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;

/**
 * Created by sangil on 2015-12-11.
 */
public class FoodRecipe implements Serializable {

    public static final String
            TABLE_NAME = "RECIPE_LIST",
            ID = "ID",
            MAIN_INGREIENT = "MAIN_INGREIENT",
            SUB_INGREIENT = "SUB_INGREIENT",
            HOW_RECIPE = "HOW_RECIPE",
            RECIPE_NAME = "RECIPE_NAME",
            STAR = "STAR",
            PICTURE = "PICTURE";


    ArrayList  Main_Ingrdient, Sub_Ingredient, How_Recipe;
    String Recipe_Name;
    int   Star;
    byte[] Picture;



    public FoodRecipe(){}

}
