package com.example.testkode.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkode.models.User
import com.example.testkode.models.UserList
import com.example.testkode.repository.RepositoryConnect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repositoryConnect = RepositoryConnect()
    private val usersList: MutableLiveData<UserList> = MutableLiveData(UserList(listOf()))
    val _usersList: MutableLiveData<UserList> = usersList

    fun reformat(department: String, list: UserList): List<User>{
        return list.items.filter { it.department == department }
    }

    fun get(){
        viewModelScope.launch(Dispatchers.IO) {
            _usersList.postValue(repositoryConnect.getUsers())
        }
    }
}