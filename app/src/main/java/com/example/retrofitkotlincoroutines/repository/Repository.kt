package com.example.retrofitkotlincoroutines.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitkotlincoroutines.Resource
import com.example.retrofitkotlincoroutines.api.RetrofitInstance
import com.example.retrofitkotlincoroutines.models.Post
import retrofit2.Response

class Repository {

    private val _data = MutableLiveData<Resource<ArrayList<Post>>>()
    val data: LiveData<Resource<ArrayList<Post>>> = _data

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(
        userId: Int,
        sort: String,
        order: String
    ){
        _data.value = Resource.Loading()
        try {
            val response = RetrofitInstance.api.getCustomPost(userId, sort, order)
            _data.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            _data.value = Resource.Error(e.message.toString())
        }
    }

    suspend fun getCustomPost2(userId: Int, options: Map<String, String>): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost2(userId, options)
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushPost2(userId: Int, id: Int, title: String, body: String): Response<Post> {
        return RetrofitInstance.api.pushPost2(userId, id, title, body)
    }
}