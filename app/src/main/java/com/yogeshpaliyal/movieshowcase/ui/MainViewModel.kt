package com.yogeshpaliyal.movieshowcase.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogeshpaliyal.movieshowcase.data.model.MovieModel
import com.yogeshpaliyal.movieshowcase.data.network.ApiInterface
import com.yogeshpaliyal.movieshowcase.utils.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val apiInterface: ApiInterface) : ViewModel() {

    private val _trendingMovies = MutableLiveData<List<MovieModel>>()
    val trendingMovies = _trendingMovies.toLiveData()

    private val _nowShowing = MutableLiveData<List<MovieModel>>()
    val nowShowing = _nowShowing.toLiveData()

    init {
        loadTrendingMovies()
        loadNowShowing()
    }

    fun loadTrendingMovies() {
        viewModelScope.launch {
            val response = apiInterface.getTrendingMovies()
            _trendingMovies.postValue(response.body()?.results)
        }
    }

    fun loadNowShowing() {
        viewModelScope.launch {
            val response = apiInterface.getNowShowing()
            _trendingMovies.postValue(response.body()?.results)
        }
    }

}