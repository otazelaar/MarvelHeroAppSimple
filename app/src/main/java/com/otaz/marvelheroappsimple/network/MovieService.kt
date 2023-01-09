package com.otaz.marvelheroappsimple.network

import com.otaz.marvelheroappsimple.network.model.MovieDto
import com.otaz.marvelheroappsimple.network.responses.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    /**
     * Search for the a movie
     */
    @GET("SearchMovie")
    suspend fun search(
        @Query("apikey") apikey: String,
        @Query("query") query: String,
    ): MovieSearchResponse

    /**
     * Return a movie by its specific ID
     */
    @GET("Title")
    suspend fun get(
        @Query("apikey") apikey: String,
        @Query("id") id: String,
    ): MovieDto

}