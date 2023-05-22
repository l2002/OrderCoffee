package com.example.ordercoffee.API;

import com.example.ordercoffee.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson GSON=new GsonBuilder()
            .setDateFormat("MM-dd-yyyy")
            .create();

    ApiService API_SERVICE=new Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
            .create(ApiService.class);

    @GET("User")
    Call<List<User>> callApi(@Query("username") String username,
                             @Query("password") String password);
}
