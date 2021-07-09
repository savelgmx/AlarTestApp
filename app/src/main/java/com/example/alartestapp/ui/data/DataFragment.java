package com.example.alartestapp.ui.data;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alartestapp.common.BasePresenter;
import com.example.alartestapp.common.PresenterFragment;
import com.example.alartestapp.common.Refreshable;
import com.example.alartestapp.model.DataResponse;

import java.util.List;

public class DataFragment extends PresenterFragment
        implements Refreshable, DataView {

    @InjectPresenter
    DataPresenter mDataPresenter;

    @ProvidePresenter
    DataPresenter providePresenter() {
        return new DataPresenter();
    }



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
