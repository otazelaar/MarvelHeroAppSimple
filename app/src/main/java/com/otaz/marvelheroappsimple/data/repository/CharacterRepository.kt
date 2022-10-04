package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.api.RetrofitInstance
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.db.CharacterDatabase

class CharacterRepository(
    val db: CharacterDatabase
) {
    suspend fun getCharacters(limit: Int, ts: String, apikey: String, hash: String) =
        RetrofitInstance.api.getCharacters(limit, ts, apikey, hash)

    suspend fun searchCharacters(nameStartsWith: String, limit: Int, ts: String, apikey: String, hash: String) =
        RetrofitInstance.api.searchForCharacters(nameStartsWith, limit, ts, apikey, hash)

    suspend fun getComicsByID(charID: Int, limit: Int, ts: String, apikey: String, hash: String) =
        RetrofitInstance.api.getComicsByID(charID, limit, ts, apikey, hash)

    suspend fun upsert(jsonCharacterResults: JsonCharacterResults) = db.getCharacterDao().upsert(jsonCharacterResults)

    fun getSavedCharacters() = db.getCharacterDao().getAllCharacters()

    suspend fun deleteCharacter(jsonCharacterResults: JsonCharacterResults) = db.getCharacterDao().deleteCharacter(jsonCharacterResults)
}