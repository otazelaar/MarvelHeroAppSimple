package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.network.model.RetrofitService

class SuperheroRepository(
    private val apiClient: RetrofitService
) {
    suspend fun getCharacters(limit: Int, ts: String, apikey: String, hash: String) =
        apiClient.getListOfSuperheroes(limit, ts, apikey, hash)
}