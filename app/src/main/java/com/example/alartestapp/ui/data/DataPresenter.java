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
        Log.d("DataPresenter",mCode);

        //здесь должен быть запрос GET http://www.alarstudios.com/test/data.cgi
        mCompositeDisposable.add(ApiUtils.getApiService().getDataResponse(this.mCode,"10")
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response ->{})
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(
                        response -> {
                          //  mView.showData(response.getUser());
                        },
                        throwable -> mView.showError()));

    }
}
