package com.example.studentapp.api;

import com.example.studentapp.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl(MainActivity.url +"/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("post/get_search_post.php")
    Call<ResultAPI> getSearchPost(@Query("key") String search);

    @GET("user/get_user.php")
    Call<ResultObjectAPI> getUser(@Query("phoneNumber") String id);
}

