package com.example.studentapp.api;

import com.example.studentapp.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl(MainActivity.mURL +"/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("post/get_search_post.php")
    Call<ResultObjectAPI> getSearchPost(@Query("key") String search);

    @GET("user/get_user.php")
    Call<ResultObjectAPI> getUser(@Query("phoneNumber") String id);

    @GET("class/get_classes.php")
    Call<ResultAPI> getClasses(@Query("studentPhone") String studentPhone);

    @GET("class/get_class_rated_from_tutor.php")
    Call<ResultAPI> getClassRatedFromTutor(@Query("tutorPhone") String tutorPhone);

    @GET("class/get_class_by_id.php")
    Call<ResultAPI> getClassById(@Query("classID") String classID);

    @Headers({"Content-Type: application/json"})
    @PUT("class/update_status.php")
    Call<ResultStringAPI> updateStatus(@Body Map<String,String> body);

    @FormUrlEncoded
    @POST("rate/add_new_rating.php")
    Call<ResultStringAPI> addNewRating(@Field("classID") String classID,
                                  @Field("rate") float rate, @Field("comment") String comment,
                                       @Field("date") String date);

    @GET("rate/get_rating_by_classID.php")
    Call<ResultAPI> getRatingByClassID(@Query("classID") String classID);

    @FormUrlEncoded
    @POST("user/login.php")
    Call<ResultObjectAPI> userLogin(@Field("phoneNumber") String phoneNumber,
                                    @Field("password") String password);
}

