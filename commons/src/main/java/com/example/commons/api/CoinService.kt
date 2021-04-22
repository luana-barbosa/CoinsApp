package com.example.commons.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinService {

    companion object {
        const val BASE_URL = "https://rest.coinapi.io"

        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun coinRetrofitApi(): CoinApi = retrofit.create(
            CoinApi::class.java
        )
    }
}