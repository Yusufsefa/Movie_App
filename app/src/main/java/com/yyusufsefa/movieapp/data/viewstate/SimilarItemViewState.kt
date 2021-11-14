package com.yyusufsefa.movieapp.data.viewstate

import com.yyusufsefa.movieapp.data.model.Movie

class SimilarItemViewState(private val movie: Movie) {

    fun getMovieName() = movie.title.orEmpty()

    fun getImageUrl() = movie.backdropPath.orEmpty()
}
