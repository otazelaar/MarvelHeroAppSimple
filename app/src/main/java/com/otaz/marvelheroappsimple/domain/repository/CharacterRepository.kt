package com.otaz.marvelheroappsimple.domain.repository

import com.otaz.marvelheroappsimple.data.remote.dto.*
import retrofit2.Call
import retrofit2.Response

/**
 * This repository
 *
 */

interface CharacterRepository {

    suspend fun getCharacters(): Response<CharacterRequestJson>
}