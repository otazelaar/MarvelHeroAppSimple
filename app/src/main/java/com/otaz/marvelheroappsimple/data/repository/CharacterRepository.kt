package com.otaz.marvelheroappsimple.data.repository

import androidx.compose.ui.semantics.SemanticsProperties.Error
import com.otaz.marvelheroappsimple.api.Marvel
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.data.source.CharacterRemoteDataSource
import com.otaz.marvelheroappsimple.db.CharacterDao
import com.otaz.marvelheroappsimple.db.CharacterDatabase
import com.otaz.marvelheroappsimple.di.AppModule
import com.otaz.marvelheroappsimple.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class CharacterRepository(
    private val apiClient: Marvel,
    private val characterDao: CharacterDao,
    private val dataSource: CharacterRemoteDataSource,
) {
    suspend fun getCharacters(): Flow<Resource<JsonCharacterRequest>> = flow {
        try {
            val response = dataSource.getCharacters()
            characterDao.upsert(response.body()!!.data.results)
        } catch (e: Exception) {
            emit(Resource.data)
        }
    }

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