package com.example.studentapp.app_interface;


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
import retrofit2.http.HTTP;
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
            .baseUrl(MainActivity.URL +"/api/")

            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

//    @GET("user/get_user.php")
//    Call<ResultAPI> getUser(@Query("phoneNumber") String phoneNumber, @Query("password") String password);

    @FormUrlEncoded
    @POST("user/get_user.php")
    Call<ResultStringAPI> getUser(@Field("phoneNumber") String phoneNumber,
                                  @Field("password") String password);

    @GET("item/get_items.php")
    Call<ResultAPI> getitems();

    @GET("item/get_item.php")
    Call<ResultObjectAPI> getitem(@Query("id") String id);


    @Headers({"Content-Type: application/json"})
    @PUT("cart/update_cart.php")
    Call<ResultStringAPI> updateCart(@Body Map<String,String> body);

    @Headers({"Content-Type: application/json"})
    @HTTP(method = "DELETE", path = "cart/delete_cart.php", hasBody = true)
    Call<ResultStringAPI> deleteCart(@Body Map<String,String> body);

    @FormUrlEncoded
    @POST("cart/add_cart.php")
    Call<ResultStringAPI> addCart(@Field("id") String id,
                            @Field("quantity") int quantity);

    @GET("cart/get_all_cart.php")
    Call<ResultAPI> getallcart();

}
