package com.example.paging3.apiService

import com.example.paging3.models.MovieResponse
import com.example.paging3.models.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET(" movie/top_rated")
    suspend fun getMovies(
        @Query("api_key") api_key:String,
        @Query("language") language:String,
        @Query("page") page:Int

    ):Response<MovieResponse>
}
