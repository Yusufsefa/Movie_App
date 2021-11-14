package com.yyusufsefa.movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.data.repository.MovieRepository
import com.yyusufsefa.movieapp.util.Resource
import com.yyusufsefa.movieapp.util.SearchStatus
import com.yyusufsefa.movieapp.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _nowPlaying = MutableLiveData<Resource<List<Movie>>>()
    val nowPlaying: LiveData<Resource<List<Movie>>> get() = _nowPlaying

    private val _upComing = MutableLiveData<Resource<List<Movie>>>()
    val upComing: LiveData<Resource<List<Movie>>> get() = _upComing

    private val _searchMovie = MutableLiveData<SearchStatus<List<Movie>>>()
    val searchMovie: LiveData<SearchStatus<List<Movie>>> get() = _searchMovie

    init {
        getNowPlayingMovie()
        getUpComing()
    }

    private fun getNowPlayingMovie() {
        viewModelScope.launch {
            movieRepository.getNowPlaying().let {
                when (it.status) {
                    Status.LOADING -> _nowPlaying.postValue(Resource.loading())
                    Status.SUCCESS -> _nowPlaying.postValue(Resource.success(it.data?.results.orEmpty()))
                    Status.ERROR -> _nowPlaying.postValue(Resource.error(it.message.toString()))
                }
            }
        }
    }

    private fun getUpComing() {
        viewModelScope.launch {
            movieRepository.getUpComing().let {
                when (it.status) {
                    Status.LOADING -> _upComing.postValue(Resource.loading())
                    Status.SUCCESS -> _upComing.postValue(Resource.success(it.data?.results.orEmpty()))
                    Status.ERROR -> _upComing.postValue(Resource.error(it.message.toString()))
                }
            }
        }
    }

    fun searchMovie(search: String) {
        viewModelScope.launch {
            movieRepository.getSearchResult(search).let {
                when (it.status) {
                    Status.LOADING -> _searchMovie.postValue(SearchStatus.Loading)
                    Status.SUCCESS -> {
                        if (it.data?.results.isNullOrEmpty()) {
                            _searchMovie.postValue(SearchStatus.Idle)
                        } else {
                            _searchMovie.postValue(SearchStatus.Success(it.data?.results.orEmpty()))
                        }
                    }
                    Status.ERROR -> _searchMovie.postValue(SearchStatus.Error(it.message.toString()))
                }
                if (search.isEmpty()) _searchMovie.postValue(SearchStatus.Empty)
            }
        }
    }
}
