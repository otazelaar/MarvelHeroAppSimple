package com.otaz.marvelheroappsimple.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.otaz.marvelheroappsimple.data.models.JsonCharComResults
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults

@Database(
    entities = [JsonCharacterResults::class, JsonCharComResults::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

    companion object {
        const val DB_NAME: String = "db_main_one"
    }
}