package com.example.daggertutorial.api

import com.example.daggertutorial.model.User
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()
}