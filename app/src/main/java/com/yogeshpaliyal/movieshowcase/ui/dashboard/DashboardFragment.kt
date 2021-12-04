package com.yogeshpaliyal.movieshowcase.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yogeshpaliyal.movieshowcase.databinding.FragmentDashboardBinding
import com.yogeshpaliyal.movieshowcase.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    private val carousedAdapter by lazy {
        HeaderCarouselAdapter()
    }

    private val mNowShowingAdapter by lazy {
        GridListAdapter()
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
}