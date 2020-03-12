package com.example.stockmanagerapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StockManagerApi {

    /**
     * @return
     */
    @GET("materials")
    Call<List<Materials>> getMaterials();

    @POST("materials/create")
    Call<Materials> createMaterial(@Body Materials materials);

    @DELETE("materials/delete/{id}")
    Call<Void> deleteMaterials(@Path("id") int id);
}
