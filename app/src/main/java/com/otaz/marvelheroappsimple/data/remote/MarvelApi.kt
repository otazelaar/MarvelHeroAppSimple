package com.otaz.marvelheroappsimple.data.remote

import com.otaz.marvelheroappsimple.data.remote.dto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    /**
     * Return the list of characters
     */
    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Response<CharacterRequestJson>
}