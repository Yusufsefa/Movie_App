package com.yyusufsefa.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.data.repository.MovieRepository
import com.yyusufsefa.movieapp.util.Resource
import com.yyusufsefa.movieapp.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _movie = MutableLiveData<Resource<Movie>>()
    val movie: LiveData<Resource<Movie>> get() = _movie

    private val _similarMovie = MutableLiveData<Resource<List<Movie>>>()
    val similarMovie: LiveData<Resource<List<Movie>>> get() = _similarMovie

    fun fetchMovie(movieId: Long) {
        viewModelScope.launch {
            repository.getMovie(movieId).let {
                when (it.status) {
                    Status.LOADING -> _movie.postValue(Resource.loading())
                    Status.SUCCESS -> _movie.postValue(Resource.success(it.data!!))
                    Status.ERROR -> _movie.postValue(Resource.error(it.message.toString()))
                }
            }
        }
    }

    fun fetchSimilarMovie(movieId: Long) {
        viewModelScope.launch {
            repository.getSimilarMovie(movieId).let {
                when (it.status) {
                    Status.LOADING -> _similarMovie.postValue(Resource.loading())
                    Status.SUCCESS -> _similarMovie.postValue(Resource.success(it.data?.results.orEmpty()))
                    Status.ERROR -> _similarMovie.postValue(Resource.error(it.message.toString()))
                }
            }
        }
    }
}
