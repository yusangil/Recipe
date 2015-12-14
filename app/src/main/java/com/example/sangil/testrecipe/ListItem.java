package com.example.sangil.testrecipe;

import java.util.Calendar;

/**
 * Created by sangil on 2015-12-11.
 */
public class ListItem {

        private String Item_name,Item_status,Item_shelfLife;
        private int Item_count;
        private int Item_gram;


        public int getItem_gram(){return this.Item_gram;}
        public String getItem_shelfLife(){return this.Item_shelfLife;}
        public String getItem_name(){
            return Item_name;
        }
        public int getItem_count(){
            return Item_count;
        }
        public String getItem_status() {return Item_status;}


        public ListItem(int Item_gram, String Item_status, String Item_shelfLife, String Item_name, int Item_count){
            this.Item_gram=Item_gram;
            this.Item_status=Item_status;
            this.Item_shelfLife=Item_shelfLife;
            this.Item_name=Item_name;
            this.Item_count=Item_count;
        }
}
