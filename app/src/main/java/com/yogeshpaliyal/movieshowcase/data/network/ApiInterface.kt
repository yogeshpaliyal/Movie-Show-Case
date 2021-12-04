package com.yogeshpaliyal.movieshowcase.data.network

import com.yogeshpaliyal.movieshowcase.data.model.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies() : Response<TrendingResponse>

    @GET("trending/movie/day")
    suspend fun getNowShowing() : Response<TrendingResponse>
}