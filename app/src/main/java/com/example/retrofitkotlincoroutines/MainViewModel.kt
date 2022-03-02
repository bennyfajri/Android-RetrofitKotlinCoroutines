package com.example.retrofitkotlincoroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitkotlincoroutines.repository.Repository
import com.example.retrofitkotlincoroutines.models.Post
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPost2: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    //Proper code
    fun getCustomPosts(userId : Int, sort: String, order: String) = repository.getCustomPosts(userId, sort, order)

    fun pushPost(post: Post){
        viewModelScope.launch {
            val response = repository.pushPost(post)
            myResponse.value = response
        }
    }

    fun pushPost2(userId: Int, id: Int, title: String, body: String){
        viewModelScope.launch {
            val response = repository.pushPost2(userId, id, title, body)
            myResponse.value = response
        }
    }
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



    fun getCustomPost2(userId: Int, options: Map<String, String>){
        viewModelScope.launch {
            val response = repository.getCustomPost2(userId, options)
            myCustomPost2.value = response
        }
    }

}