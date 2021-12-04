package com.yogeshpaliyal.movieshowcase.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogeshpaliyal.movieshowcase.data.model.MovieModel
import com.yogeshpaliyal.movieshowcase.databinding.ItemNowShowingBinding
import com.yogeshpaliyal.movieshowcase.ui.MoviesDiffUtil

class GridListAdapter :
    ListAdapter<MovieModel, GridListAdapter.ViewHolder>(
        AsyncDifferConfig.Builder(
            MoviesDiffUtil()
        ).build()
    ) {
    inner class ViewHolder(val binding: ItemNowShowingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieModel: MovieModel) {
            Glide.with(binding.img).load(movieModel.getPosterPath()).into(binding.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNowShowingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}