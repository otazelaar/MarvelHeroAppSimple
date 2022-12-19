package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.api.Marvel
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.db.CharacterDao
import com.otaz.marvelheroappsimple.db.CharacterDatabase
import com.otaz.marvelheroappsimple.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiClient: Marvel,
    private val characterDao: CharacterDao,
    private val globalScope: CoroutineScope
) {

    suspend fun getCharacters(limit: Int, ts: String, apikey: String, hash: String) =
        globalScope.launch {
            apiClient.getCharacters(limit, ts, apikey, hash)
        }

    suspend fun searchCharacters(nameStartsWith: String, limit: Int, ts: String, apikey: String, hash: String) =
        globalScope.launch {
            apiClient.searchForCharacters(nameStartsWith, limit, ts, apikey, hash)
        }

    suspend fun getComicsByID(charID: Int, limit: Int, ts: String, apikey: String, hash: String) =
        globalScope.launch {
            apiClient.getComicsByID(charID, limit, ts, apikey, hash)
        }

    suspend fun upsert(jsonCharacterResults: JsonCharacterResults) =
        globalScope.launch {
            characterDao.upsert(jsonCharacterResults)
        }

    suspend fun getSavedCharacters() =
        characterDao.getAllCharacters()

    suspend fun deleteCharacter(jsonCharacterResults: JsonCharacterResults) =
        globalScope.launch {
            characterDao.deleteCharacter(jsonCharacterResults)
        }
}