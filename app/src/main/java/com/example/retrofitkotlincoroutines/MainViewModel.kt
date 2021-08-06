package com.example.retrofitkotlincoroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitkotlincoroutines.Repository.Repository
import com.example.retrofitkotlincoroutines.models.Post
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPost: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPost2(number2: Int){
        viewModelScope.launch {
            val response = repository.getPost2(number2)
            myResponse2.value = response
        }
    }

    fun getCustomPosts(userId : Int, sort: String, order: String){
        viewModelScope.launch {
            val response = repository.getCustomPosts(userId, sort, order)
            myCustomPost.value = response
        }
    }

}