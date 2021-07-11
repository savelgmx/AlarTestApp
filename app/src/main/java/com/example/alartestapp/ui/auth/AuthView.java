package com.example.alartestapp.ui.auth;

import com.example.alartestapp.common.BaseView;
import com.example.alartestapp.model.AuthResponse;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface AuthView extends BaseView {
    void showAuthResponse(@NonNull List<AuthResponse> authresponses);
    void openDataFragment(@NonNull String code);
}
