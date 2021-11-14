package com.yyusufsefa.movieapp.data.viewstate

import com.yyusufsefa.movieapp.data.model.Movie

class DetailMovieViewState(private val movie: Movie) {

    fun getMovieName() = movie.title.orEmpty()

    fun getImageUrl() = movie.backdropPath.orEmpty()

    fun getMovieDesc() = movie.overview

    fun getMovieReleaseDate() = movie.releaseDate.orEmpty()

    fun getRate() = movie.voteAverage.toString()
}
