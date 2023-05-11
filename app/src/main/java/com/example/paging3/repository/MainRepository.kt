package com.example.paging3.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.paging3.apiService.MovieService
import com.example.paging3.models.Movies

class MainRepository(private val retrofit:MovieService) {
    fun getAllMovies(): LiveData<PagingData<Movies>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(retrofit)
            }
            , initialKey = 1
        ).liveData
    }
}