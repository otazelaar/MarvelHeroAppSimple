package com.otaz.marvelheroappsimple.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jsonCharacterResults: JsonCharacterResults)

    @Query("SELECT * FROM jsonCharacterResults")
    fun getAllCharacters(): List<JsonCharacterResults>
}