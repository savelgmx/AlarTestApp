package com.example.alartestapp.api;

import android.support.annotation.NonNull;


import com.example.alartestapp.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class ApiKeyInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url().newBuilder()
                .addQueryParameter("username", BuildConfig.USERNAME)
                .addQueryParameter("password",BuildConfig.PASSWORD)
                .build();
        request = request.newBuilder().url(httpUrl).build();

        return chain.proceed(request);
    }
}