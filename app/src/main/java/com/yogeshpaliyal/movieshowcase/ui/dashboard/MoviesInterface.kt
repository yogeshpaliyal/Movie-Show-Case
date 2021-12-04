package com.yogeshpaliyal.movieshowcase.ui.dashboard

import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.yogeshpaliyal.movieshowcase.data.model.MovieModel

interface MoviesInterface {
    fun onItemClick(movieModel: MovieModel, cardView: CardView, imageView: ImageView)
}