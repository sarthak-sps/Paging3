package com.example.paging3.apiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    private const val base_url= "https://api.themoviedb.org/3/"

     fun getInstance():Retrofit
     {
         return Retrofit.Builder()
             .baseUrl(base_url)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
     }
}