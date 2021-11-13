package com.example.testkode.retrofit

import com.example.testkode.models.UserList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServerApi {

    @Headers("Content-Type: application/json"
        ,"Prefer: code=200, dynamic=true" )
    @GET("users")
    fun getUsers(): Call<UserList>

    @Headers("Content-Type: application/json"
        , "Prefer: code=200, example=success")
    @GET("users")
    fun searchUsers(@Query("name") searchTextView: String): Call<UserList>
}