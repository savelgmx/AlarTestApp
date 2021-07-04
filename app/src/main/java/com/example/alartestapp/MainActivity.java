package com.example.alartestapp;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alartestapp.api.AlarApi;
import com.example.alartestapp.api.ApiUtils;
import com.example.alartestapp.model.AuthResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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

    Retrofit retrofit;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Пока обращение к серверу через ретрофит делаем
        //прямо здесь
 /*       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
*/        callAlarApi();
    }
    private void callAlarApi(){
        Request request = new Request.Builder()
                .url(BuildConfig.API_URL)
                .build();

        OkHttpClient client = ApiUtils.getBasicAuthClient(
                BuildConfig.USERNAME,
                BuildConfig.PASSWORD,
                true);
        client.newCall(request).enqueue(new Callback() {
            //используем Handler, чтобы показывать ошибки в Main потоке, т.к. наши коллбеки возвращаются в рабочем потоке
            Handler mainHandler = new Handler(getApplication().getMainLooper());

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //   mainHandler.post(() -> showMessage(R.string.request_error));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                mainHandler.post(() -> {
                    if (!response.isSuccessful()) {
                        //todo добавить полноценную обработку ошибок по кодам ответа от сервера и телу запроса
                        //showMessage(R.string.auth_error);
                    } else {
                        try {
                            Gson gson = new Gson();
                            JsonObject json = gson.fromJson(response.body().string(), JsonObject.class);
                            AuthResponse  authResponse = gson.fromJson(json.get("data"), AuthResponse.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}




    
    
    
    
