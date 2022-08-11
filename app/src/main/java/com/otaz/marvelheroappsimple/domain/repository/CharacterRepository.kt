package com.otaz.marvelheroappsimple.domain.repository

import com.otaz.marvelheroappsimple.data.remote.dto.MarvelDto

interface CharacterRepository {

    suspend fun getCharacters(): List<MarvelDto>

    suspend fun getComicsByID(): List<MarvelDto>
}