package com.yyusufsefa.movieapp.util

sealed class SearchStatus<out T> {
    data class Success<T>(val list: T) : SearchStatus<T>()
    data class Error(val exception: String) : SearchStatus<Nothing>()
    object Loading : SearchStatus<Nothing>()
    object Empty : SearchStatus<Nothing>()
    object Idle : SearchStatus<Nothing>()
}
