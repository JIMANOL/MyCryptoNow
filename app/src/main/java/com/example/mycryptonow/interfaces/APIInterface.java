package com.example.mycryptonow.interfaces;


import com.example.mycryptonow.models.CryptoCoinMarket;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers("X-CMC_PRO_API_KEY: 129f5bfb-69e8-45a6-9c84-4266681a22a2")
    @GET("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest")
    Call<CryptoCoinMarket> doGetUserList(@Query("start") String inicio, @Query("limit") String limite, @Query("convert") String moneda);


}
