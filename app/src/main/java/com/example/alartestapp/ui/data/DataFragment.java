package com.example.alartestapp.ui.data;

import android.app.MediaRouteButton;
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

    private DataAdapter mDataAdapter;

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
