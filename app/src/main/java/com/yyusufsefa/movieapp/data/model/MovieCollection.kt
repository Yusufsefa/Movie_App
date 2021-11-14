package com.yyusufsefa.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieCollection(
    val id: Long?,
    val name: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)
