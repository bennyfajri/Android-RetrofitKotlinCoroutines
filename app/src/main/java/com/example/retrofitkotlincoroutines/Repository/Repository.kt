package com.example.retrofitkotlincoroutines.Repository

import com.example.retrofitkotlincoroutines.api.RetrofitInstance
import com.example.retrofitkotlincoroutines.models.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post>{
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int, sort: String, order: String): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost(userId, sort, order)
    }

}