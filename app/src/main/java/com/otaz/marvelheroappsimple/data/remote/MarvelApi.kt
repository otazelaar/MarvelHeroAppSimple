package com.otaz.marvelheroappsimple.data.remote

import com.otaz.marvelheroappsimple.data.remote.dto.MarvelDto
import com.otaz.marvelheroappsimple.data.remote.dto.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    /**
     * Return the list of characters
     */
    @GET("characters")
    fun getCharacters(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): List<MarvelDto>

    /**
     * Return the list of comics of a specific character
     */
    @GET("characters")
    suspend fun getComicsByID(
        @Query("characterID") id: Int,
        @Query("comics") comics: String,
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): List<MarvelDto>
}