package com.otaz.marvelheroappsimple.data.source

import com.otaz.marvelheroappsimple.api.Marvel
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import retrofit2.Response
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val apiClient: Marvel,
) {
    /**
     * Fetches the latest news from the network and returns the result.
     * This executes on an IO-optimized thread pool, the function is main-safe.
     */
    suspend fun getCharacters(limit: Int, ts: String, apikey: String, hash: String): Response<JsonCharacterRequest> =
        apiClient.getCharacters(limit, ts, apikey, hash)

}