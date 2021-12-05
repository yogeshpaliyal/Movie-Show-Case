package com.yogeshpaliyal.movieshowcase.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.yogeshpaliyal.movieshowcase.R
import com.yogeshpaliyal.movieshowcase.data.model.MovieModel
import com.yogeshpaliyal.movieshowcase.databinding.FragmentDashboardBinding
import com.yogeshpaliyal.movieshowcase.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.PagerSnapHelper

import androidx.recyclerview.widget.SnapHelper




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

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewHeader)

        mViewModel.trendingMovies.observe(viewLifecycleOwner) {
            carousedAdapter.submitList(it)
        }

        mViewModel.trendingMovies.observe(viewLifecycleOwner) {
            mNowShowingAdapter.submitList(it)
        }

        binding.nestedScrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener{
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                val topContentHeight = binding.recyclerViewHeader.measuredHeight + binding.txtNowShowing.measuredHeight
                if(scrollY >= topContentHeight){
                    binding.toolBar.title = binding.txtNowShowing.text
                }else{
                    binding.toolBar.title = "Movies"
                }
            }

        })
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