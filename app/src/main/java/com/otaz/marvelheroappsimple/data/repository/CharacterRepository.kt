package com.otaz.marvelheroappsimple.data.repository

import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.db.CharacterDatabase
import com.otaz.marvelheroappsimple.di.AppModule

class CharacterRepository(
    private val db: CharacterDatabase
) {
    suspend fun getCharacters(limit: Int, ts: String, apikey: String, hash: String) =
        AppModule.provideApiClient().getCharacters(limit, ts, apikey, hash)

    suspend fun searchCharacters(nameStartsWith: String, limit: Int, ts: String, apikey: String, hash: String) =
        AppModule.provideApiClient().searchForCharacters(nameStartsWith, limit, ts, apikey, hash)

    suspend fun getComicsByID(charID: Int, limit: Int, ts: String, apikey: String, hash: String) =
        AppModule.provideApiClient().getComicsByID(charID, limit, ts, apikey, hash)

    suspend fun upsert(jsonCharacterResults: JsonCharacterResults) =
        db.getCharacterDao().upsert(jsonCharacterResults)

    fun getSavedCharacters() =
        db.getCharacterDao().getAllCharacters()

    suspend fun deleteCharacter(jsonCharacterResults: JsonCharacterResults) =
        db.getCharacterDao().deleteCharacter(jsonCharacterResults)

}