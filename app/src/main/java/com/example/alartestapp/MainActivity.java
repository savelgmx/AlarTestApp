package com.example.alartestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alartestapp.api.AlarApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.alartestapp.api.AlarApi.BASE_URL;


public class MainActivity extends AppCompatActivity {
    
    Retrofit retrofit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //Пока обращение к серверу через ретрофит делаем
        //прямо здесь
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
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
        callAlarApi();
    }
    private void callAlarApi(){
        AlarApi alarApi = retrofit.create(AlarApi.class);
        alarApi.getAuthResponce("test","123");
        alarApi.getDataResponse("12334244","100");
        
    }
    
    
    
    
}