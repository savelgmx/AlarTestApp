package com.example.alartestapp.ui.auth;

import android.support.v4.app.Fragment;

import com.example.alartestapp.common.SingleFragmentActivity;

//https://github.com/matthiasbruns/rxandroid2-retrofit2
// http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/


public class AuthActivity extends SingleFragmentActivity {

    @Override
    protected  Fragment getFragment(){ return AuthFragment.newInstance();}
    }

