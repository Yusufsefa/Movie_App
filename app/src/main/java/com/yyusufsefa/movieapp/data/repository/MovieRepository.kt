package com.yyusufsefa.movieapp.data.repository

import com.yyusufsefa.movieapp.common.BaseRepository
import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.data.model.MovieResponse
import com.yyusufsefa.movieapp.network.Api
import com.yyusufsefa.movieapp.util.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: Api) : BaseRepository() {

    suspend fun getNowPlaying(): Resource<MovieResponse> {
        return getResult {
            api.getNowPlaying()
        }
    }

    suspend fun getUpComing(): Resource<MovieResponse> {
        return getResult {
            api.getUpcoming()
        }
    }

    suspend fun getMovie(movieId: Long): Resource<Movie> {
        return getResult {
            api.getMovie(movieId)
        }
    }

    suspend fun getSimilarMovie(movieId: Long): Resource<MovieResponse> {
        return getResult {
            api.getSimilarMovie(movieId)
        }
    }

    suspend fun getSearchResult(search: String): Resource<MovieResponse> {
        return getResult {
            api.getSearchResult(search)
        }
    }
}
