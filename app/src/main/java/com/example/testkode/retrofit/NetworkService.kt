package com.example.testkode.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    private const val baseUrl = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"
class NetworkService{

    companion object {
        fun getRetroInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}