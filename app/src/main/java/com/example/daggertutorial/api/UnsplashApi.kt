package com.example.daggertutorial.api

import android.os.Build
import com.example.daggertutorial.BuildConfig
import com.example.daggertutorial.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {
    companion object{
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }
    @Headers("Accept-Version: v1", "Authorization: $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UnsplashResponse
}