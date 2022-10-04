package com.otaz.marvelheroappsimple.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(jsonCharacterResults: JsonCharacterResults): Long

    @Query("SELECT * FROM room_character_results")
    fun getAllCharacters(): LiveData<List<JsonCharacterResults>>

    @Delete
    suspend fun deleteCharacter(jsonCharacterResults: JsonCharacterResults)


}