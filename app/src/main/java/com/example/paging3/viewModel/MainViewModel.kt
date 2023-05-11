package com.example.paging3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.models.Movies
import com.example.paging3.repository.MainRepository

class MainViewModel(private val mainRepository: MainRepository):ViewModel() {

fun getMovieList():LiveData<PagingData<Movies>>
{

    return mainRepository.getAllMovies().cachedIn(viewModelScope)
}

}