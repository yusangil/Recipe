package com.example.sangil.testrecipe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends FragmentActivity implements OnClickListener{

    private LinearLayout ll_recipe;
    private LinearLayout ll_kitchen;
    private LinearLayout ll_find;
    private LinearLayout ll_user;
    private ImageView image_home;
    private ImageView image_friends;
    private ImageView image_message;
    private ImageView image_more;
    //Fragment 관리자
    private FragmentManager fm = this.getSupportFragmentManager();
    private FragmentTransaction ft;
    private Fragment fragmentPage1;
    private Fragment fragmentPage2;
    private Fragment fragmentPage3;
    private Fragment fragmentPage4;

    final String TAG = "MainActivity";
    public final static int FRAGMENT_ONE = 0;
    public final static int FRAGMENT_TWO = 1;
    public final static int FRAGMENT_THREE = 2;
    public final static int FRAGMENT_FOUR =3;
    int mCurrentFragmentIndex = FRAGMENT_ONE;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        //트랜잭션을 시작할.
        ft = fm.beginTransaction();
        turn_kitchen();

        //커밋할
        ft.commit();
    }

    private void initView() {
        ll_recipe = (LinearLayout) findViewById(R.id.ll_recipe);
        ll_kitchen = (LinearLayout)findViewById(R.id.ll_kitchen);
        ll_find = (LinearLayout)findViewById(R.id.ll_find);
        ll_user = (LinearLayout)findViewById(R.id.ll_user);

        image_home = (ImageView)findViewById(R.id.image_recipe);
        image_friends = (ImageView)findViewById(R.id.image_kitchen);
        image_message = (ImageView)findViewById(R.id.image_photo);
        image_more = (ImageView)findViewById(R.id.image_option);
        ll_recipe.setOnClickListener(this);
        ll_kitchen.setOnClickListener(this);
        ll_find.setOnClickListener(this);
        ll_user.setOnClickListener(this);
        ll_recipe.setSelected(true);
        image_home.setSelected(true);
        ll_recipe.setBackgroundColor(Color.LTGRAY);
        ll_find.setBackgroundColor(Color.GRAY);
        ll_kitchen.setBackgroundColor(Color.GRAY);
        ll_user.setBackgroundColor(Color.GRAY);

    }

     @Override
    public void onClick(View v) {
        ft = fm.beginTransaction();
        setSelected();
        switch (v.getId()) {
            case R.id.ll_recipe:
                turn_recipe();
                ll_recipe.setSelected(true);
                image_home.setSelected(true);
                break;
            case R.id.ll_kitchen:
                turn_kitchen();
                ll_kitchen.setSelected(true);
                image_friends.setSelected(true);
                break;
            case R.id.ll_find:
                turn_find();
                ll_find.setSelected(true);
                image_message.setSelected(true);
                break;
            case R.id.ll_user:
                turn_user();
                ll_user.setSelected(true);
                image_more.setSelected(true);
                break;
        }
        ft.commit();
    }
    /**
     * 설정 단추를 누르면 선택한 모든 것이
     */
    private void setSelected(){
        ll_recipe.setSelected(false);
        ll_kitchen.setSelected(false);
        ll_find.setSelected(false);
        ll_user.setSelected(false);
        image_home.setSelected(false);
        image_friends.setSelected(false);
        image_message.setSelected(false);
        image_more.setSelected(false);
        if(fragmentPage1 != null){
            //Fragment 숨기기
            ft.hide(fragmentPage1);
        }
        if(fragmentPage2 != null){
            ft.hide(fragmentPage2);
        }
        if(fragmentPage3 != null){
            ft.hide(fragmentPage3);
        }
        if(fragmentPage4 != null){
            ft.hide(fragmentPage4);
        }
    }

    private void turn_recipe(){
        mCurrentFragmentIndex = FRAGMENT_ONE;
        fragmentReplace(mCurrentFragmentIndex);
        ll_recipe.setBackgroundColor(Color.LTGRAY);
        ll_find.setBackgroundColor(Color.GRAY);
        ll_kitchen.setBackgroundColor(Color.GRAY);
        ll_user.setBackgroundColor(Color.GRAY);
    }
    private void turn_kitchen(){
        mCurrentFragmentIndex = FRAGMENT_TWO;
        fragmentReplace(mCurrentFragmentIndex);
        ll_recipe.setBackgroundColor(Color.GRAY);
        ll_find.setBackgroundColor(Color.GRAY);
        ll_kitchen.setBackgroundColor(Color.LTGRAY);
        ll_user.setBackgroundColor(Color.GRAY);
    }
    private void turn_find(){
        mCurrentFragmentIndex = FRAGMENT_THREE;
        fragmentReplace(mCurrentFragmentIndex);
        ll_recipe.setBackgroundColor(Color.GRAY);
        ll_find.setBackgroundColor(Color.LTGRAY);
        ll_kitchen.setBackgroundColor(Color.GRAY);
        ll_user.setBackgroundColor(Color.GRAY);
    }
    private void turn_user(){
        mCurrentFragmentIndex = FRAGMENT_FOUR;
        fragmentReplace(mCurrentFragmentIndex);
        ll_recipe.setBackgroundColor(Color.GRAY);
        ll_find.setBackgroundColor(Color.GRAY);
        ll_kitchen.setBackgroundColor(Color.GRAY);
        ll_user.setBackgroundColor(Color.LTGRAY);
    }
    public void fragmentReplace(int reqNewFragmentIndex) {

        Fragment newFragment = null;

        Log.d(TAG, "fragmentReplace " + reqNewFragmentIndex);

        newFragment = getFragment(reqNewFragmentIndex);

        // replace fragment
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.fl_content, newFragment);

        // Commit the transaction
        transaction.commit();

    }
    private Fragment getFragment(int idx) {
        Fragment newFragment = null;

        switch (idx) {
            case FRAGMENT_ONE:
                newFragment = new Tab1();

                break;
            case FRAGMENT_TWO:
                newFragment = new Tab2();
                break;
            case FRAGMENT_THREE:
                newFragment = new Tab3();
                break;
            case FRAGMENT_FOUR:
                newFragment = new Tab4();
                break;

            default:
                Log.d(TAG, "Unhandle case");
                break;
        }

        return newFragment;
    }


}