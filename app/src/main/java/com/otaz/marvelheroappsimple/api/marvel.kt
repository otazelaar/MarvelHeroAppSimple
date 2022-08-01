package com.otaz.marvelheroappsimple.api

import com.otaz.marvelheroappsimple.models.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Marvel {

    @GET("characters")
    fun getCharacters(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Call<Characters>
}