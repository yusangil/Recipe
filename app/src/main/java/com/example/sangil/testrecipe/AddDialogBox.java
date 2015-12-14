package com.example.sangil.testrecipe;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

/**
 * Created by sangil on 2015-12-09.
 */
public class AddDialogBox extends DialogFragment {
    private Button mConfirm, mCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.add_menu, container, false);

        return view;
    }

}
