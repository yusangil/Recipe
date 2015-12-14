package com.example.sangil.testrecipe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sangil on 2015-12-07.
 */
public class Optionview extends View {

    private Drawable image;
    int viewWidth, viewHeight,imageWidth,imageHeight,x,y;

    public Optionview(Context context, AttributeSet attrs) {
        super(context, attrs);
        //이미지를 drawable폴더에서 가져오기
        image = this.getResources().getDrawable(R.drawable.picture);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //위치 가운데오게 하는 알고리즘
        viewWidth= this.getWidth();//화면의 사이즈
        viewHeight= this.getHeight();
        imageWidth= image.getIntrinsicWidth()*4;  // 이미지의 사이즈
        imageHeight= image.getIntrinsicHeight()*4;
        x = viewWidth/ 2 -imageWidth/ 2; //이미지 시작 포인트
        y = viewHeight/ 2 -imageHeight/ 2;
        super.onSizeChanged(w, h, oldw, oldh);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        image.setBounds(x, y, x + imageWidth, y + imageHeight);
        image.draw(canvas);
        super.onDraw(canvas);
    }
}
