package com.yyusufsefa.movieapp.network

import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.data.model.MovieResponse
import com.yyusufsefa.movieapp.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") key: String = API_KEY
    ): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") key: String = API_KEY
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Long,
        @Query("api_key") key: String = API_KEY
    ): Response<Movie>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovie(
        @Path("movie_id") movieId: Long,
        @Query("api_key") key: String = API_KEY
    ): Response<MovieResponse>

    @GET("search/movie")
    suspend fun getSearchResult(
        @Query("query") searchText: String,
        @Query("api_key") key: String = API_KEY
    ): Response<MovieResponse>
}
