package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.common.Constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.common.Constants.Companion.COMICS
import com.otaz.marvelheroappsimple.common.Constants.Companion.hash
import com.otaz.marvelheroappsimple.common.Constants.Companion.limit
import com.otaz.marvelheroappsimple.common.Constants.Companion.timeStamp
import com.otaz.marvelheroappsimple.data.remote.MarvelApi
import com.otaz.marvelheroappsimple.data.remote.dto.MarvelDto
import com.otaz.marvelheroappsimple.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : CharacterRepository {

    override suspend fun getCharacters(): List<MarvelDto> {
        return api.getCharacters(limit, timeStamp, API_KEY, hash())
    }

    override suspend fun getComicsByID(): List<MarvelDto> {
        return api.getComicsByID(1011334, COMICS, limit, timeStamp, API_KEY, hash())
    }
}