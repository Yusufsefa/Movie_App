package com.yyusufsefa.movieapp.ui.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yyusufsefa.movieapp.R
import com.yyusufsefa.movieapp.common.BaseFragment
import com.yyusufsefa.movieapp.data.viewstate.DetailMovieViewState
import com.yyusufsefa.movieapp.databinding.FragmentDetailBinding
import com.yyusufsefa.movieapp.ui.adapter.SimilarMovieAdapter
import com.yyusufsefa.movieapp.util.Status
import com.yyusufsefa.movieapp.util.hide
import com.yyusufsefa.movieapp.util.show
import com.yyusufsefa.movieapp.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    private val similarMovieAdapter by lazy { SimilarMovieAdapter() }

    private val navArgs by navArgs<DetailFragmentArgs>()

    override fun init() {
        binding.rvSimilarMovie.adapter = similarMovieAdapter

        similarMovieAdapter.setOnItemClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentSelf(it.id!!))
        }
    }

    override fun observeData() {

        viewModel.fetchMovie(navArgs.movieId)
        viewModel.fetchSimilarMovie(navArgs.movieId)

        viewModel.movie.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> binding.prgLoading.show()
                Status.SUCCESS -> {
                    binding.prgLoading.hide()
                    binding.detailViewState = DetailMovieViewState(it.data!!)
                }
                Status.ERROR -> {
                    requireContext().toast(it.message.toString())
                }
            }
        }

        viewModel.similarMovie.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> binding.prgLoading.show()
                Status.SUCCESS -> {
                    binding.prgLoading.hide()
                    similarMovieAdapter.submitList(it.data.orEmpty())
                }
                Status.ERROR -> requireContext().toast(it.message.toString())
            }
        }
    }
}
