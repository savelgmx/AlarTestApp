package com.example.alartestapp.api;

import com.example.alartestapp.BuildConfig;
import com.example.alartestapp.model.AuthResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {

    public static final List<Class<?>> NETWORK_EXCEPTIONS = Arrays.asList(
            UnknownHostException.class,
            SocketTimeoutException.class,
            ConnectException.class
    );

    private static OkHttpClient sClient;
    private static Retrofit sRetrofit;
    private static Gson sGson;
    private static AlarApi sApi;


    private static OkHttpClient getClient() {
        if (sClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.addInterceptor(new ApiKeyInterceptor());
            if (!BuildConfig.BUILD_TYPE.contains("release")) {
                builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }
            sClient = builder.build();
        }
        return sClient;
    }

    private static Retrofit getRetrofit() {
        if (sGson == null) {
            sGson = new Gson();
        }
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    // need for interceptors
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create(sGson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    public static AlarApi getApiService() {
        if (sApi == null) {
            sApi = getRetrofit().create(AlarApi.class);
        }
        return sApi;
    }

    //and now with other arguments
    public static AlarApi getApiService(String username, String password, boolean createNewInstance) {
        if (createNewInstance || sApi == null) {


            sApi = rebuildRetrofit(getBasicAuthClient(
                    username,
                    password,
                    true))
                    .create(AlarApi.class);
        }
        return sApi;
    }

    private static GsonConverterFactory buildUserGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Adding custom deserializer
        gsonBuilder.registerTypeAdapter(AuthResponse.class, new AuthResponseDeserializer());
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }


    private static Retrofit rebuildRetrofit(OkHttpClient client) {

        if (sGson == null) {
            sGson = new Gson();
        }

        sRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                // need for interceptors
                .client(client)
                .addConverterFactory(buildUserGsonConverter())
                .addConverterFactory(GsonConverterFactory.create(sGson))
                //строка ниже нужна чтобы избежать ошибки Exeption
                //Unable to create call adapter for io.reactivex.Single
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return sRetrofit;
    }



    public static OkHttpClient getBasicAuthClient(final String username, final String password, boolean createNewInstance) {
        if (createNewInstance || sClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.authenticator((route, response) -> {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder().header("Authorization", credential).build();
            });
            if (!BuildConfig.BUILD_TYPE.contains("release")) {
                builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }

            sClient = builder.build();
        }
        return sClient;
    }


}
