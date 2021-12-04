package com.yogeshpaliyal.movieshowcase.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.yogeshpaliyal.movieshowcase.R
import com.yogeshpaliyal.movieshowcase.data.model.MovieModel
import com.yogeshpaliyal.movieshowcase.databinding.FragmentDashboardBinding
import com.yogeshpaliyal.movieshowcase.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(), MoviesInterface {

    private lateinit var binding: FragmentDashboardBinding

    private val carousedAdapter by lazy {
        HeaderCarouselAdapter(this)
    }

    private val mNowShowingAdapter by lazy {
        GridListAdapter(this)
    }

    private val mViewModel by activityViewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewHeader.adapter = carousedAdapter
        binding.rvNowShowing.adapter = mNowShowingAdapter

        mViewModel.trendingMovies.observe(viewLifecycleOwner) {
            carousedAdapter.submitList(it)
        }

        mViewModel.trendingMovies.observe(viewLifecycleOwner) {
            mNowShowingAdapter.submitList(it)
        }
    }

    override fun onItemClick(movieModel: MovieModel, cardView: CardView, imageView: ImageView) {
        mViewModel.setSelectedMovie(movieModel)

        val extras = FragmentNavigatorExtras(cardView to "card", imageView to "image")

        findNavController().navigate(
            R.id.action_dashboardFragment_to_detailFragment,
            null,
            null,
            extras
        )
    }
}