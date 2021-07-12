package com.example.alartestapp.ui.data;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.alartestapp.common.BasePresenter;

@InjectViewState
public class DataPresenter extends BasePresenter<DataView> {
    private final DataView mView;
    private String mCode;

    public DataPresenter(DataView view){
        mView = view;
    };

    public void getDataResponse(String code){
        this.mCode = code;
        Log.d("DataPresenter",mCode);

    }
}
