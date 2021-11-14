package com.yyusufsefa.movieapp.data.viewstate

import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.util.textShorting

class SliderItemViewState(private val movie: Movie) {

    fun getMovieName() = movie.title.orEmpty()

    fun getImageUrl() = movie.backdropPath.orEmpty()

    fun getMovieDesc() = textShorting(movie.overview.toString(), 100)
}
