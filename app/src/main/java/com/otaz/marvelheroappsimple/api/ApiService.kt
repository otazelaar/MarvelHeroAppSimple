package com.otaz.marvelheroappsimple.api

import com.otaz.marvelheroappsimple.data.models.JsonMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * Return the list of the most popular movies
     */
    @GET("MostPopularMovies")
    suspend fun getMostPopularMovies(
        @Query("apikey") apikey: String,
    ): Response<JsonMovies>

}