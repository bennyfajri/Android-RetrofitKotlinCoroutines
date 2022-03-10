package com.example.retrofitkotlincoroutines.di

import com.example.retrofitkotlincoroutines.BuildConfig
import com.example.retrofitkotlincoroutines.api.SimpleApi
import com.example.retrofitkotlincoroutines.repository.Repository
import com.example.retrofitkotlincoroutines.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Injection {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        val loggingInterceptor = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit by lazy{
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit
    }

    @Provides
    @Singleton
    fun providesSimpleApi(retrofit: Retrofit): SimpleApi =
        retrofit.create(SimpleApi::class.java)

}