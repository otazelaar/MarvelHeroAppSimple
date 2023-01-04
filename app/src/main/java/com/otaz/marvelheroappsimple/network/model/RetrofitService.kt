package com.otaz.marvelheroappsimple.network.model

import com.otaz.marvelheroappsimple.network.model.responses.SuperheroResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    // Provide list of all characters within the chosen limit
    @GET("characters")
    suspend fun getListOfSuperheroes(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): SuperheroResponse

    // Provide Specific Character and associated data
    @GET("characters/{charID}")
    suspend fun getSuperheroByID(
        @Path("charID") charID: Int,
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): SuperheroNetworkEntity
}