package com.example.alartestapp.ui;

import android.support.v4.app.Fragment;

import com.example.alartestapp.common.BasePresenter;
import com.example.alartestapp.common.PresenterFragment;
import com.example.alartestapp.common.Refreshable;
import com.example.alartestapp.model.DataResponse;

import java.util.List;


public class DataFragment extends PresenterFragment
        implements Refreshable,DataView {


        public static DataFragment newInstance() {
        return new DataFragment();
    }

        @Override
        protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onRefreshData() {

    }

    @Override
    public void showData(@org.jetbrains.annotations.NotNull List<DataResponse> data) {

    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

    }

    @Override
    public void showError() {

    }
}
