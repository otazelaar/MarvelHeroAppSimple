package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.common.Constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.common.Constants.Companion.COMICS
import com.otaz.marvelheroappsimple.common.Constants.Companion.hash
import com.otaz.marvelheroappsimple.common.Constants.Companion.limit
import com.otaz.marvelheroappsimple.common.Constants.Companion.timeStamp
import com.otaz.marvelheroappsimple.data.remote.MarvelApi
import com.otaz.marvelheroappsimple.data.remote.dto.*
import com.otaz.marvelheroappsimple.domain.repository.CharacterRepository
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : CharacterRepository {

    override suspend fun getCharacters(): Response<CharacterRequestJson> {
        return api.getCharacters(limit, timeStamp, API_KEY, hash())
    }

}