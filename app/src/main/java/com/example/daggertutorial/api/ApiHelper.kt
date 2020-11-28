package com.example.daggertutorial.api

import com.example.daggertutorial.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}