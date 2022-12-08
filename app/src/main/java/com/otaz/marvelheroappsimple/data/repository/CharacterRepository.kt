package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.api.Marvel
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.db.CharacterDao
import com.otaz.marvelheroappsimple.db.CharacterDatabase
import com.otaz.marvelheroappsimple.di.AppModule
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiClient: Marvel,
    private val characterDao: CharacterDao,
    private val globalScope: CoroutineScope
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