package com.example.testkode.repository

import com.example.testkode.models.UserList
import com.example.testkode.response.SuccessfulResponse
import java.lang.Exception

class RepositoryConnect private constructor(private val successfulResponse: SuccessfulResponse){

    suspend fun getUsers(): UserList?{

        return try{
            successfulResponse.getUsers()
        } catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    companion object{

        private var repository: RepositoryConnect? = null

        operator fun invoke(): RepositoryConnect{

            if(repository == null) repository = RepositoryConnect(SuccessfulResponse())

            return repository!!
        }
    }
}