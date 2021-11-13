package com.example.testkode.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkode.models.User
import com.example.testkode.models.UserList
import com.example.testkode.retrofit.NetworkService
import com.example.testkode.retrofit.ServerApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    var recyclerListData: MutableLiveData<UserList> = MutableLiveData()

    fun getUserList(): MutableLiveData<UserList>
    {
        return recyclerListData
    }
    fun getUsersData(){
        val retroInstance = NetworkService.getRetroInstance().create(ServerApi::class.java)
        val call = retroInstance.getUsers()
        call.enqueue(object : Callback<UserList>{
            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
                Log.i("TAG", t.message.toString())
            }
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.body() !=null){
                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                    Log.d("TAG", response.raw().toString())
                }
            }
        })
    }
}