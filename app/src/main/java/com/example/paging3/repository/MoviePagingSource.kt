package com.example.paging3.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.apiService.MovieService
import com.example.paging3.models.MovieResponse
import com.example.paging3.models.Movies
import java.lang.Exception

class MoviePagingSource( val apiService:MovieService):PagingSource<Int,Movies>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        val page = params.key ?: 1
        return try {

            val response = apiService.getMovies("enter your api_key", "en-US", page)
            LoadResult.Page(
                data = response.body()!!.results,
                nextKey = page + 1,
                prevKey = if (page == 1) null else -1,
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }
}