package com.otaz.marvelheroappsimple.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jsonCharacterResults: JsonCharacterResults)

}