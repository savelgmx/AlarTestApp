package com.example.alartestapp.common;

import com.arellomobile.mvp.MvpAppCompatFragment;

/**
 * Created by andrew on 07.06.2019.
 */


public abstract class PresenterFragment extends MvpAppCompatFragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onDetach() {
        if (getPresenter() != null) {
            getPresenter().disposeAll();
        }
        super.onDetach();
    }
}