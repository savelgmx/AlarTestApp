package com.example.alartestapp.ui.data;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.alartestapp.api.ApiUtils;
import com.example.alartestapp.common.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DataPresenter extends BasePresenter<DataView> {
    private final DataView mView;
    private String mCode;

    public DataPresenter(DataView view){
        mView = view;
    };

    public void getDataResponse(String code){
        this.mCode = code;
        //здесь должен быть запрос GET http://www.alarstudios.com/test/data.cgi
        mCompositeDisposable.add(ApiUtils.getApiService().getDataResponse(this.mCode,"10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() ->getViewState().hideRefresh())
                .subscribe(
                        response -> {
                            getViewState().showData(response.getData());

                        },
                        throwable -> {
                         getViewState().showError();
                        }
                )
        );

    }
}
