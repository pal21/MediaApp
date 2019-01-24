package com.example.mediaapp.activity.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

        //Trailing slash is needed
        public static final String BASE_URL="https://dl.dropboxusercontent.com/";
        public static Retrofit retrofitInstance = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

}
