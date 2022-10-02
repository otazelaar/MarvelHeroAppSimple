package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.api.RetrofitInstance
import com.otaz.marvelheroappsimple.db.CharacterDatabase
import com.otaz.marvelheroappsimple.utils.constants
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import retrofit2.http.Query

class CharacterRepository(
    val db: CharacterDatabase
) {
    suspend fun getCharacters(limit: Int, ts: String, apikey: String, hash: String,) =
        RetrofitInstance.api.getCharacters(LIMIT, TIMESTAMP, API_KEY, hash())
}