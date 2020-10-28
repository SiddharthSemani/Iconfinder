package com.example.icon.data.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://api.iconfinder.com/v4/";
    private static Retrofit apiInstance = null;

    public static Retrofit getApiClient() {
        if (apiInstance == null) {
            apiInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return apiInstance;
    }
}
