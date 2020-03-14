package com.example.stockmanagerapp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StockManagerApi {

    /**
     * @return
     */
    @GET("materials")
    Call<List<Materials>> getMaterials();

    @FormUrlEncoded
    @POST("user/create")
    Call<ResponseBody> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password

    );

    @POST("materials/create")
    Call<Materials> createMaterial(@Body Materials materials);


    @DELETE("materials/delete/{id}")
    Call<Void> deleteMaterials(@Path("id") int id);

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


}
