package com.example.daggertutorial.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnsplashUrls,
    val user: UnsplashUser
) : Parcelable {
    @Parcelize
    data class UnsplashUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val thumb: String
    ) : Parcelable

    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String
    ) : Parcelable {
        val attributionUrl get() = "https//unsplash.com/$username?utm_source=DaggerTutorial&utm_medium=referral"
    }
}

