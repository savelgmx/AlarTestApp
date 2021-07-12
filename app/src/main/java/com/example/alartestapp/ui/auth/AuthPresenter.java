package com.example.alartestapp.ui.auth;

import android.content.Intent;

import com.example.alartestapp.BuildConfig;
import com.example.alartestapp.api.ApiUtils;
import com.example.alartestapp.common.BasePresenter;
import com.example.alartestapp.model.AuthResponse;
import com.example.alartestapp.ui.data.DataActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthPresenter extends BasePresenter<AuthView> {

    public AuthPresenter(AuthView view){

    }

    public void AuthResponse(){
        mCompositeDisposable.add(

        ApiUtils.getApiService().getAuthResponce(BuildConfig.USERNAME,BuildConfig.PASSWORD)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> getViewState().showAuthResponse(response))
                .map(AuthResponse::getCode)
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
                .doOnSubscribe(disposable -> getViewState().showRefresh())
            //    .doFinally(getViewState()::hideRefresh)
                .subscribe(code->{
                            //здесь данные которые успешно извлечены из user после вызова API
                            //далее действия при успехе
                    getViewState().openDataFragment(code);


                        },throwable -> getViewState().showError())
                            //  Toast.makeText(getActivity(),R.string.auth_error, Toast.LENGTH_SHORT).show();


        );

/*
        mCompositeDisposable.add(
                ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
                        .subscribeOn(Schedulers.io())
                        .doOnSuccess(mStorage::insertProjects)
                        .onErrorReturn(throwable ->
                                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> getViewState().showRefresh())
                        .doFinally(getViewState()::hideRefresh)
                        .subscribe(
                                response -> getViewState().showProjects(response.getProjects()),
                                throwable -> getViewState().showError())
        );
    }

 */

    }





}
