package com.yogeshpaliyal.movieshowcase.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogeshpaliyal.movieshowcase.data.model.MovieModel
import com.yogeshpaliyal.movieshowcase.databinding.ItemDashboardCarouselBinding
import com.yogeshpaliyal.movieshowcase.ui.MoviesDiffUtil

class HeaderCarouselAdapter(private val listener: MoviesInterface) :
    ListAdapter<MovieModel, HeaderCarouselAdapter.ViewHolder>(
        AsyncDifferConfig.Builder(
            MoviesDiffUtil()
        ).build()
    ) {
    inner class ViewHolder(val binding: ItemDashboardCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieModel: MovieModel) {
            Glide.with(binding.img).load(movieModel.getBackdropPath()).into(binding.img)
            binding.cardView.setOnClickListener {
                listener.onItemClick(currentList[bindingAdapterPosition], binding.cardView, binding.img)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDashboardCarouselBinding.inflate(
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