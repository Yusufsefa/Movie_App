package com.yyusufsefa.movieapp.ui.main

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.yyusufsefa.movieapp.R
import com.yyusufsefa.movieapp.common.BaseFragment
import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.databinding.FragmentMainBinding
import com.yyusufsefa.movieapp.ui.adapter.MovieAdapter
import com.yyusufsefa.movieapp.ui.adapter.SearchAdapter
import com.yyusufsefa.movieapp.ui.adapter.ViewPagerAdapter
import com.yyusufsefa.movieapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    private val movieAdapter by lazy { MovieAdapter() }
    private val vpAdapter by lazy { ViewPagerAdapter() }
    private val searchAdapter by lazy { SearchAdapter() }

    private var currentMovieList: List<Movie> by Delegates.observable(
        emptyList(),
        { _, oldValue, newValue ->
            if (oldValue != newValue) {
                movieAdapter.submitList(newValue)
            }
        }
    )

    override fun init() {
        initRecyclerview()
        initViewPager()

        binding.swipeToRefreshLayout.setOnRefreshListener {
            initViewPager()
            initRecyclerview()
            binding.swipeToRefreshLayout.isRefreshing = false
        }

        movieAdapter.setOnItemClickListener { findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it.id!!)) }

        searchAdapter.setOnItemClickListener { findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it.id!!)) }

        vpAdapter.setOnItemClickListener { findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it.id!!)) }
    }

    override fun observeData() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean = false

            override fun onQueryTextChange(query: String?): Boolean {
                viewModel.searchMovie(query!!)
                viewModel.searchMovie.observe(viewLifecycleOwner) { searchStatus ->
                    when (searchStatus) {
                        SearchStatus.Loading -> TODO()
                        is SearchStatus.Success -> initSearchAdapter(searchStatus.list)
                        is SearchStatus.Error -> requireContext().toast(searchStatus.exception)
                        SearchStatus.Idle -> {
                            binding.run {
                                vpSlider.show()
                                intoTabLayout.show()
                            }
                            binding.rvMain.adapter = movieAdapter
                            movieAdapter.submitList(currentMovieList)
                        }
                        SearchStatus.Empty -> {
                            binding.run {
                                vpSlider.show()
                                intoTabLayout.show()
                            }
                            binding.rvMain.adapter = movieAdapter
                            movieAdapter.submitList(currentMovieList)
                        }
                    }
                }
                return false
            }
        })
    }

    private fun initRecyclerview() {

        binding.rvMain.adapter = movieAdapter

        viewModel.upComing.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.LOADING -> {
                    binding.prgLoading.show()
                }
                Status.SUCCESS -> {
                    currentMovieList = result.data.orEmpty()
                    binding.prgLoading.hide()
                }
                Status.ERROR -> {
                    requireContext().toast(result.message.toString())
                    binding.prgLoading.hide()
                }
            }
        }
    }

    private fun initSearchAdapter(movies: List<Movie>) {
        binding.run {
            vpSlider.hide()
            intoTabLayout.hide()
        }
        binding.rvMain.adapter = searchAdapter
        searchAdapter.submitList(movies)
    }

    private fun initViewPager() {
        viewModel.nowPlaying.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.LOADING -> {
                    binding.prgLoading.show()
                }
                Status.SUCCESS -> {
                    vpAdapter.setNewList(result.data.orEmpty())
                    binding.prgLoading.hide()
                }
                Status.ERROR -> {
                    requireContext().toast(result.message.toString())
                    binding.prgLoading.hide()
                }
            }
        }
        binding.vpSlider.adapter = vpAdapter
        TabLayoutMediator(
            binding.intoTabLayout,
            binding.vpSlider
        ) { tab, position -> }.attach()
        vpAdapter.setOnItemClickListener {
            Log.d("slider", it.title.toString())
        }
    }
}
