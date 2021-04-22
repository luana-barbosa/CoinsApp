package com.example.commons.api

import com.example.commons.model.Coin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApi {

    @GET("v1/assets?")
    fun getAllList(@Query("apiKey") api: String): retrofit2.Call<List<Coin>>

    @GET("v1/assets?/asset_id")
    fun getAllDetails(@Path("asset_id") asset_id: String, @Query("apiKey") apiKey: String): Call<Coin>
}