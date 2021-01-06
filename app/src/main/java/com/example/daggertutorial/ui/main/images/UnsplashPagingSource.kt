package com.example.daggertutorial.ui.main.images

import android.util.Log
import com.example.daggertutorial.api.UnsplashApi
import androidx.paging.PagingSource
import com.example.daggertutorial.model.UnsplashPhoto

private val STARTING_PAGE_INDEX = 1
class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
)
//    : PagingSource<Int, UnsplashPhoto>()
{

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
//        val position = params.key ?: STARTING_PAGE_INDEX
//    }
}