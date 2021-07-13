package com.example.alartestapp.ui.auth;

import com.example.alartestapp.common.BaseView;
import com.example.alartestapp.model.Auth;

import io.reactivex.annotations.NonNull;

public interface AuthView extends BaseView {
    void showAuthResponse(@NonNull Auth authresponse);
    void openDataFragment(@NonNull String code);
}
