package com.otaz.marvelheroappsimple.domain.repository

import com.otaz.marvelheroappsimple.data.remote.dto.Item
import com.otaz.marvelheroappsimple.data.remote.dto.Result
import com.otaz.marvelheroappsimple.domain.model.ResultData

/**
 * This repository 
 *
 */

interface CharacterRepository {

    suspend fun getCharacters(): Result

    suspend fun getComicsByID(charID: String): List<Item>
}