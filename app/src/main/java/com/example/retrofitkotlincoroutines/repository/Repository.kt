package com.example.retrofitkotlincoroutines.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.retrofitkotlincoroutines.Resource
import com.example.retrofitkotlincoroutines.api.SimpleApi
import com.example.retrofitkotlincoroutines.models.Post
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val simpleApi: SimpleApi
){

    suspend fun getPost(): Response<Post> {
        return simpleApi.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post> {
        return simpleApi.getPost2(number)
    }

    fun getCustomPosts(
        userId: Int? = null,
        sort: String,
        order: String
    ): LiveData<Resource<ArrayList<Post>>> = liveData {
        val data = MutableLiveData<Resource<ArrayList<Post>>>()
        emit(Resource.Loading)
        try {
            val response = simpleApi.getCustomPost(userId, sort, order)
            data.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            Log.d("Repository", "getCustomPost: ${e.message.toString()}")
            emit(Resource.Error(e.message.toString()))
        }
        emitSource(data)
    }

    suspend fun getCustomPost2(userId: Int, options: Map<String, String>): Response<List<Post>> {
        return simpleApi.getCustomPost2(userId, options)
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return simpleApi.pushPost(post)
    }

    suspend fun pushPost2(userId: Int, id: Int, title: String, body: String): Response<Post> {
        return simpleApi.pushPost2(userId, id, title, body)
    }
//
//    companion object {
//        @Volatile
//        private var instance: Repository? = null
//        fun getInstance() : Repository =
//            instance ?: synchronized(this) {
//                instance ?: Repository()
//            }.also { instance = it }
//    }
}