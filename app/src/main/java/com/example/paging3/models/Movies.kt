package com.example.paging3.models

data class MovieResponse(val page: Int, val results: List<Movies>)

data class Movies(val original_title: String, val poster_path: String, val overview: String)
