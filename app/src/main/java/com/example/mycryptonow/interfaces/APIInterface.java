package com.example.mycryptonow.interfaces;


import com.example.mycryptonow.models.CryptoCoinMarket;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers("X-CMC_PRO_API_KEY: 2f825572-b047-4ffe-a403-efb17ae2e006")
    @GET("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest")
    Call<CryptoCoinMarket> doGetUserList(@Query("start") String inicio, @Query("limit") String limite, @Query("convert") String moneda);


}
