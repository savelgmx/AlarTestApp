package com.example.alartestapp.ui.data;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alartestapp.R;
import com.example.alartestapp.common.BasePresenter;
import com.example.alartestapp.common.PresenterFragment;
import com.example.alartestapp.common.RefreshOwner;
import com.example.alartestapp.common.Refreshable;
import com.example.alartestapp.model.Data;

import java.util.List;

public class DataFragment extends PresenterFragment
        implements Refreshable, DataAdapter.OnItemClickListener,DataView {

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private String mCode;//код который передается для запроса
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
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof RefreshOwner) {
            mRefreshOwner = ((RefreshOwner) context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mErrorView = view.findViewById(R.id.errorView);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mCode = getArguments().getString(CODE_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle("Data");
        }

        mDataAdapter = new DataAdapter((DataAdapter.OnItemClickListener) this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mDataAdapter);

        onRefreshData();
    }




    @Override
    public void showData(List<Data.Datum> data) {

        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mDataAdapter.addData(data, true);


    }

    @Override
    public void showRefresh() {
        mRefreshOwner.setRefreshState(true);
    }

    @Override
    public void hideRefresh() {
        mRefreshOwner.setRefreshState(false);

    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }



    @Override
    public void onItemClick(String lan, String lon) {
        //здесь мы должны вызывать фрагмент показа карты и передать в него координаты
        //Intent

    }
}
