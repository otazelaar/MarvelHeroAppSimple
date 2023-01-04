package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.api.ApiService

class CharacterRepository(
    private val apiService: ApiService
) {

    // Playing with flows
    suspend fun getMostPopularMovies(apikey: String) =
        apiService.getMostPopularMovies(apikey)

//
//    suspend fun searchCharacters(nameStartsWith: String, limit: Int, ts: String, apikey: String, hash: String) =
//        AppModule.provideApiClient().searchForCharacters(nameStartsWith, limit, ts, apikey, hash)
//
//    suspend fun getComicsByID(charID: Int, limit: Int, ts: String, apikey: String, hash: String) =
//        AppModule.provideApiClient().getComicsByID(charID, limit, ts, apikey, hash)
//
//    suspend fun upsert(jsonCharacterResults: JsonCharacterResults) =
//        db.getCharacterDao().upsert(jsonCharacterResults)
//
//    fun getSavedCharacters() =
//        db.getCharacterDao().getAllCharacters()
//
//    suspend fun deleteCharacter(jsonCharacterResults: JsonCharacterResults) =
//        db.getCharacterDao().deleteCharacter(jsonCharacterResults)

}