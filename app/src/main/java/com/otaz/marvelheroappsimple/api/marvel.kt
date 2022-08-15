package com.otaz.marvelheroappsimple.api

import com.otaz.marvelheroappsimple.models.JsonCharComRequest
import com.otaz.marvelheroappsimple.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.models.JsonComicRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Marvel {

    /**
     * Return the list of all characters
     */
    @GET("characters")
    fun getCharacters(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Call<JsonCharacterRequest>

    /**
     * Return the list of all comics
     */
    @GET("comics")
    fun getComics(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Call<JsonComicRequest>

    /**
     * Return the list of comics of a specific character *charID*
     */
    @GET("characters/{charID}/comics")
    fun getComicsByID(
        @Path("charID") charID: Int,
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Call<JsonCharComRequest>
}