package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.api.Marvel
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.db.CharacterDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val apiClient: Marvel,
    private val characterDao: CharacterDao,
) {
    suspend fun getCharacters(limit: Int, ts: String, apikey: String, hash: String) =
        apiClient.getCharacters(limit, ts, apikey, hash)

    suspend fun searchCharacters(nameStartsWith: String, limit: Int, ts: String, apikey: String, hash: String) =
        apiClient.searchForCharacters(nameStartsWith, limit, ts, apikey, hash)

    suspend fun getComicsByID(charID: Int, limit: Int, ts: String, apikey: String, hash: String) =
        apiClient.getComicsByID(charID, limit, ts, apikey, hash)

    suspend fun upsert(jsonCharacterResults: JsonCharacterResults) =
        characterDao.upsert(jsonCharacterResults)

    fun getSavedCharacters() =
        characterDao.getAllCharacters()

    suspend fun deleteCharacter(jsonCharacterResults: JsonCharacterResults) =
        characterDao.deleteCharacter(jsonCharacterResults)

}