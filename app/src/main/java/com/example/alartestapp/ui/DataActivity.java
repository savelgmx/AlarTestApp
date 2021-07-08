package com.example.alartestapp.ui;

import android.support.v4.app.Fragment;

import com.example.alartestapp.common.SingleFragmentActivity;


public class DataActivity extends SingleFragmentActivity {

    @Override
    protected  Fragment getFragment(){ return DataFragment.newInstance();}
    }

