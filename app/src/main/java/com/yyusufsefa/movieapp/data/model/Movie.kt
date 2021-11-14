package com.yyusufsefa.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("belongs_to_collection") val collection: MovieCollection?,
    val budget: Long?,
    val genres: List<Genre>?,
    val homepage: String?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("genre_ids") val genreIDS: List<Long>?,
    val id: Long?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productCompanies: List<MovieCompanies>?,
    @SerializedName("production_countries") val production_countries: List<MovieCountries>?,
    @SerializedName("release_date") val releaseDate: String?,
    val revenue: Long?,
    val runtime: Int?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Long?
)