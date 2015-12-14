package com.example.sangil.testrecipe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ListLowViewHolder extends RecyclerView.ViewHolder {


    protected TextView showStatus,showName,showCount,showGram,showShelfLife;

    public ListLowViewHolder(View view) {
        super(view);
        showStatus = (TextView) view.findViewById(R.id.col_status);
        showName = (TextView) view.findViewById(R.id.col_name);
        showCount = (TextView) view.findViewById(R.id.col_count);
        showGram = (TextView) view.findViewById(R.id.col_gram);
        showShelfLife = (TextView) view.findViewById(R.id.col_shelfLife);
        view.setClickable(false);
    }
}