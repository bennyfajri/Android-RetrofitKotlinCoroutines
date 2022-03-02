package com.example.retrofitkotlincoroutines.di

import com.example.retrofitkotlincoroutines.repository.Repository

object Injection {
    fun provideRepository() : Repository {
        return Repository.getInstance()
    }
}