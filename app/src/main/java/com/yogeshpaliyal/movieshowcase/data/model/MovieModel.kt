package com.yogeshpaliyal.movieshowcase.data.model

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

data class MovieModel(
    val id: Int,
    val poster_path: String,
    val backdrop_path: String,
    val title: String,
    val release_date: String,
    val overview: String,
){
    fun getPosterPath() = IMAGE_BASE_URL + poster_path

    fun getBackdropPath() = IMAGE_BASE_URL + backdrop_path
}
