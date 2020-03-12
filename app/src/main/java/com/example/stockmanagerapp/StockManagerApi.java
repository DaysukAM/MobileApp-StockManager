package com.example.stockmanagerapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StockManagerApi {

    /**
     * @return
     */
    @GET("materials")
    Call<List<Materials>> getMaterials();

    @POST("materials/create")
    Call<Materials> createMaterial(@Body Materials materials);
}
