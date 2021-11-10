package com.example.testkode.network

import com.example.testkode.response.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {

    @GET("users")
    fun getUsers(): Call<MutableList<User>>
}