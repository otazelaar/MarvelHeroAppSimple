package com.otaz.marvelheroappsimple.api

import com.otaz.marvelheroappsimple.domain.models.Characters
import com.otaz.marvelheroappsimple.domain.models.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Marvel {
    /**
     * Return the list of characters
     */
    @GET("characters")
    fun getCharacters(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Call<Characters>

    /**
     * Return the list of comics of a specific character
     */
    @GET("characters")
    fun getCharacterComics(
        @Query("characterID") id: Int,
        @Query("comics") comics: String,
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Call<Characters>
}