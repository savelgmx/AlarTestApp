package com.example.alartestapp.ui.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.alartestapp.common.SingleFragmentActivity;

public class DataActivity extends SingleFragmentActivity {
    public static final String CODE_KEY = "CODE_KEY";


    @Override
    protected Fragment getFragment() {
        if (getIntent()!= null){
            return DataFragment.newInstance(getIntent().getExtras());
        }
        throw new IllegalStateException("getIntent cannot be null");


    }
}


