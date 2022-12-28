package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.api.Marvel
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.data.source.CharacterRemoteDataSource
import com.otaz.marvelheroappsimple.db.CharacterDao
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.QUERY_PAGE_SIZE
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash

class CharacterRepository(
    private val apiClient: Marvel,
    private val characterDao: CharacterDao,
    private val dataSource: CharacterRemoteDataSource,
) {
    // Testing CharacterRemoteDataSource with just the getCharacters suspend function.
    // The goal would be to later bring all suspend functions over to DataSources that will be consumed byt the repository
    suspend fun getCharacters() =
        dataSource.getCharacters(limit = QUERY_PAGE_SIZE, ts = TIMESTAMP, apikey = API_KEY, hash = hash())

    // Not sure how to being the searchCharacters suspend function over to the CharacterRemoteDataSource because
    // it requires data "nameStartsWith" from the user. Not sure how to implement this yet...
    suspend fun searchCharacters(nameStartsWith: String, limit: Int, ts: String, apikey: String, hash: String) =
        apiClient.searchForCharacters(nameStartsWith, limit, ts, apikey, hash)

    // The function below also requires data from the user. Not sure what to do here
    suspend fun getComicsByID(charID: Int, limit: Int, ts: String, apikey: String, hash: String) =
        apiClient.getComicsByID(charID, limit, ts, apikey, hash)

    suspend fun upsert(jsonCharacterResults: JsonCharacterResults) =
        characterDao.upsert(jsonCharacterResults)

    // Need to make this a suspend function but not sure how that changes things elsewhere in the app as of yet.
    fun getSavedCharacters() =
        characterDao.getAllCharacters()

    suspend fun deleteCharacter(jsonCharacterResults: JsonCharacterResults) =
        characterDao.deleteCharacter(jsonCharacterResults)

}