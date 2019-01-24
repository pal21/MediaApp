package com.example.mediaapp.activity.rest;

import com.example.mediaapp.activity.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<News> getNews();

}
