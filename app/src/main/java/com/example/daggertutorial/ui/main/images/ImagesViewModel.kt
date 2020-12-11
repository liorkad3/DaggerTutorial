package com.example.daggertutorial.ui.main.images

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.daggertutorial.repository.UnsplashRepository
import javax.inject.Inject

class ImagesViewModel @ViewModelInject constructor(
    private val unsplashRepository: UnsplashRepository): ViewModel(){
}