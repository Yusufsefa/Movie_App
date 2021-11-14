package com.yyusufsefa.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieCompanies(
    val id: Long?,
    val name: String?,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("origin_country") val originCountry: String?
)