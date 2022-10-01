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
class MainViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {

    private val _trendingMovies = MutableLiveData<List<MovieModel>>()
    val trendingMovies = _trendingMovies.toLiveData()

    private val _nowShowing = MutableLiveData<List<MovieModel>>()
    val nowShowing = _nowShowing.toLiveData()

    private val _selectedMovieModel = MutableLiveData<MovieModel>()
    val selectedMovieModel = _selectedMovieModel.toLiveData()

    var selectedModel : MovieModel?= null

    init {
        loadTrendingMovies()
        loadNowShowing()
    }

    fun setSelectedMovie(movieModel: MovieModel){
        selectedModel = movieModel
        _selectedMovieModel.value = movieModel
    }

    private fun loadTrendingMovies() {
        viewModelScope.launch {
            val response = apiInterface.getTrendingMovies()
            _trendingMovies.postValue(response.body()?.results)
        }
    }

    private fun loadNowShowing() {
        viewModelScope.launch {
            val response = apiInterface.getNowShowing()
            _trendingMovies.postValue(response.body()?.results)
        }
    }

}