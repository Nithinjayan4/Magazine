package com.example.magazine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit;

    public static Retrofit getRetrofit(String mURL) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder().baseUrl("http://"+mURL+"/magazine/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit;
    }


}
