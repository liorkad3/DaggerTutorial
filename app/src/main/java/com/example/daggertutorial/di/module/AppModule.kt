package com.example.daggertutorial.di.module

import com.example.daggertutorial.api.ApiHelper
import com.example.daggertutorial.api.ApiHelperImpl
import com.example.daggertutorial.api.ApiService
import com.example.daggertutorial.api.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "https://api.unsplash.com/"

    @AccessKey
    @Provides
    fun provideAccessKey() = "-Y7366hOICT2yeFFb4WGEbE6IvysarEyDc9eqO6evOg"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = if (true){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @BaseUrl BASE_URL: String): Retrofit =
        Retrofit.Builder()
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi = retrofit.create(UnsplashApi::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}