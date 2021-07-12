package com.example.alartestapp.ui.data;

import android.app.MediaRouteButton;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alartestapp.common.BasePresenter;
import com.example.alartestapp.common.PresenterFragment;
import com.example.alartestapp.common.RefreshOwner;
import com.example.alartestapp.common.Refreshable;
import com.example.alartestapp.model.DataResponse;

import java.util.List;

public class DataFragment extends PresenterFragment
        implements Refreshable, DataView {

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private String mCode;//код который пердается для запроса
    private DataAdapter mDataAdapter;

    public static final String CODE_KEY = "CODE_KEY";

    @InjectPresenter
    DataPresenter mDataPresenter;

    @ProvidePresenter
    DataPresenter providePresenter() {
        return new DataPresenter(this);
    }

    public static DataFragment newInstance(Bundle args) {
        DataFragment fragment = new DataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onRefreshData() {
        mDataPresenter.getDataResponse(mCode);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mCode = getArguments().getString(CODE_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(mCode);
        }

/*
        mDataView.setVisibility(View.VISIBLE);

        mDataresenter = new DataPresenter(this);
*/

        onRefreshData();
    }




    @Override
    public void showData(List<DataResponse> data) {

        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mDataAdapter.addData(data, true);


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
