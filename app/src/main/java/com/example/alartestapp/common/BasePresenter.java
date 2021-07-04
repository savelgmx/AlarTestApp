package com.example.alartestapp.common;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by andrew on 07.06.2019.
 */

public class BasePresenter<V extends BaseView> extends MvpPresenter<V>{
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    //нужна для отписок от подписок (Subscibe в RxJava2)

    public void disposeAll(){
        mCompositeDisposable.clear();
    }
}
