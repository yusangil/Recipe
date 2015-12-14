package com.example.sangil.testrecipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sangil on 2015-12-03.
 */
public class Tab4 extends Fragment implements OnClickListener{

    final String TAG = "Tab4";

    private LinearLayout login_sns;
    private LinearLayout share_family;
    private LinearLayout make_list;
    private LinearLayout last_alarm;
    private ImageView image_facebook;
    private ImageView image_family;
    private ImageView image_list;
    private ImageView image_alarm;
    private TextView text_facebook;
    private TextView text_family;
    private TextView text_list;
    private TextView text_alarm;
    //Fragment 관리자

    private Fragment fragmentPage1;
    private Fragment fragmentPage2;
    private Fragment fragmentPage3;
    private Fragment fragmentPage4;
    public final static int FRAGMENT_ONE = 0;
    public final static int FRAGMENT_TWO = 1;
    public final static int FRAGMENT_THREE = 2;
    public final static int FRAGMENT_FOUR =3;

    int mCurrentFragmentIndex = FRAGMENT_ONE;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "옵션", Toast.LENGTH_SHORT)
                .show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlayout_04, container, false);
        initView(view);
        return view;
    }
    public void onClick(View v) {
        Fragment sns_Fragment = new SNS();
        Fragment family_Fragment = new Family();
        Fragment make_Fragment = new List();
        Fragment alarm_Fragment = new Alarm();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        setSelected();
        switch (v.getId()) {
            case R.id.login_sns:

                login_sns.setSelected(true);
                image_facebook.setSelected(true);
                text_facebook.setSelected(true);
                transaction.replace(R.id.fl_content, sns_Fragment);
                transaction.commit();
                break;

            case R.id.share_family:
                share_family.setSelected(true);
                image_family.setSelected(true);
                text_family.setSelected(true);
                transaction.replace(R.id.fl_content, family_Fragment);
                transaction.commit();
                break;

            case R.id.make_list:
                make_list.setSelected(true);
                image_list.setSelected(true);
                text_list.setSelected(true);
                transaction.replace(R.id.fl_content, make_Fragment);
                transaction.commit();
                break;

            case R.id.last_alarm:
                last_alarm.setSelected(true);
                image_alarm.setSelected(true);
                text_alarm.setSelected(true);
                transaction.replace(R.id.fl_content, alarm_Fragment);
                transaction.commit();
                break;
        }

    }
    private void initView(View view) {
        login_sns = (LinearLayout) view.findViewById(R.id.login_sns);
        share_family = (LinearLayout)view.findViewById(R.id.share_family);
        make_list = (LinearLayout)view.findViewById(R.id.make_list);
        last_alarm = (LinearLayout)view.findViewById(R.id.last_alarm);

        image_facebook = (ImageView)view.findViewById(R.id.image_facebook);
        image_family = (ImageView)view.findViewById(R.id.image_family);
        image_list = (ImageView)view.findViewById(R.id.image_list);
        image_alarm = (ImageView)view.findViewById(R.id.image_alarm);

        text_facebook = (TextView)view.findViewById(R.id.text_facebook);
        text_family = (TextView)view.findViewById(R.id.text_family);
        text_list = (TextView)view.findViewById(R.id.text_list);
        text_alarm = (TextView)view.findViewById(R.id.text_alarm);

        login_sns.setOnClickListener(this);
        share_family.setOnClickListener(this);
        make_list.setOnClickListener(this);
        last_alarm.setOnClickListener(this);

        login_sns.setSelected(true);
        text_facebook.setSelected(true);
        image_facebook.setSelected(true);


    }
    /**
     * 설정 단추를 누르면 선택한 모든 것이
     */
    private void setSelected(){
        login_sns.setSelected(false);
        share_family.setSelected(false);
        make_list.setSelected(false);
        last_alarm.setSelected(false);

        image_facebook.setSelected(false);
        image_family.setSelected(false);
        image_list.setSelected(false);
        image_alarm.setSelected(false);

        text_facebook.setSelected(false);
        text_family.setSelected(false);
        text_list.setSelected(false);
        text_alarm.setSelected(false);
    }
}
