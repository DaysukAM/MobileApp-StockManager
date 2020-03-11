package com.example.stockmanagerapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StockManagerApi {

    /**
     * @return
     */
    @GET("materials")
    Call<List<Materials>> getMaterials();
}
