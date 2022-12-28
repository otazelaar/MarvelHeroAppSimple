package com.otaz.marvelheroappsimple.data.source

import com.otaz.marvelheroappsimple.api.Marvel
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.utils.constants
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.QUERY_PAGE_SIZE
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import retrofit2.Response
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val apiClient: Marvel,
){
    suspend fun getCharacters(): Response<JsonCharacterRequest> =
        apiClient.getCharacters(QUERY_PAGE_SIZE, TIMESTAMP, API_KEY, hash())
}