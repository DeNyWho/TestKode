package com.example.testkode.response

import com.example.testkode.models.UserList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://stoplight.io/"

interface SuccessfulResponse{

    @Headers("Content-Type: application/json"
        ,"Prefer: code=200, example=success" )
    @GET("/mocks/kode-education/trainee-test/25143926/users")
    suspend fun getUsers(): UserList

    companion object{

        private var successfulResponse: SuccessfulResponse? = null

        operator fun invoke(): SuccessfulResponse{

            if(successfulResponse == null){

                successfulResponse = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SuccessfulResponse::class.java)
            }
            return successfulResponse!!
        }
    }
}