package com.example.alartestapp;


import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.alartestapp.api.AlarApi;
import com.example.alartestapp.api.ApiUtils;
import com.example.alartestapp.model.AuthResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.alartestapp.api.AlarApi.BASE_URL;


public class MainActivity extends AppCompatActivity {

    private Disposable mDisposable= new Disposable() {
        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }
    };
    private AlarApi mApi;
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          callAlarApi();
    }
    private void callAlarApi(){


        mDisposable = ApiUtils.getApiService().getAuthResponce(BuildConfig.USERNAME,BuildConfig.PASSWORD)
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


    }



    private void showResponseStatus(String response){
        Toast.makeText(this,"This is query result"+response,Toast.LENGTH_SHORT).show();

    }
}




    
    
    
    
