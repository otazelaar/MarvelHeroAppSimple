package com.otaz.marvelheroappsimple.data.remote

import android.security.identity.ResultData
import com.otaz.marvelheroappsimple.data.remote.dto.Comics
import com.otaz.marvelheroappsimple.data.remote.dto.Item
import com.otaz.marvelheroappsimple.data.remote.dto.MarvelDto
import com.otaz.marvelheroappsimple.data.remote.dto.Result
import com.otaz.marvelheroappsimple.domain.model.ComicsData
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
    ): List<Result>

    /**
     * Return the list of comics of a specific character
     */
    @GET("characters/{charID}")
    suspend fun getComicsByID(
        @Path("charID") charID: String,
        @Query("comics") comics: String,
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): List<Item>
}