package com.yogeshpaliyal.movieshowcase.ui

import androidx.recyclerview.widget.DiffUtil
import com.yogeshpaliyal.movieshowcase.data.model.MovieModel

class MoviesDiffUtil : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.toString() == newItem.toString()
    }
}