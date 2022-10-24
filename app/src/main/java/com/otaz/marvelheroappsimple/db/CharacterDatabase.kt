package com.otaz.marvelheroappsimple.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.otaz.marvelheroappsimple.data.models.JsonCharComResults
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults

@Database(
    entities = [JsonCharacterResults::class, JsonCharComResults::class],
    version = 1,
)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

    companion object {
        @Volatile
        private var instance: CharacterDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CharacterDatabase::class.java,
                "character_db_redo.db"
            ).build()
    }
}