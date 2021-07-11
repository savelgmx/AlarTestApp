package com.example.alartestapp.ui.auth;

import com.example.alartestapp.BuildConfig;
import com.example.alartestapp.api.ApiUtils;
import com.example.alartestapp.common.BasePresenter;
import com.example.alartestapp.model.AuthResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthPresenter extends BasePresenter<AuthView> {

    public AuthPresenter(AuthView view){

    }

    public void AuthResponse(){

        //https://github.com/matthiasbruns/rxandroid2-retrofit2
        // http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/

/*
        mCompositeDisposable = ApiUtils.getApiService().getAuthResponce(BuildConfig.USERNAME, BuildConfig.PASSWORD)
                .map(AuthResponse::getCode)
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(response -> mIsErrorVisible.postValue(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
                .subscribe(
                        response -> showResponseStatus(response),
                        throwable -> {

                        });

*/


    }





}
