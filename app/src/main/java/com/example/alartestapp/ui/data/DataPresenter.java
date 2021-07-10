package com.example.alartestapp.ui.data;

import com.arellomobile.mvp.InjectViewState;
import com.example.alartestapp.common.BasePresenter;

@InjectViewState
public class DataPresenter extends BasePresenter<DataView> {
    private final DataView mView;

    public DataPresenter(DataView view){
        mView = view;
    };

    public void getDataResponse(String code){

    }
}
