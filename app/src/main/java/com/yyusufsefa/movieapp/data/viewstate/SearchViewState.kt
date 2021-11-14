package com.yyusufsefa.movieapp.data.viewstate

import com.yyusufsefa.movieapp.data.model.Movie

class SearchViewState(private val movie: Movie) {

    fun getTitle() = movie.title.orEmpty()
}
