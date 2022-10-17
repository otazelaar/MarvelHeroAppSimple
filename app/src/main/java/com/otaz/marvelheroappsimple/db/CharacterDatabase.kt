package com.otaz.marvelheroappsimple.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.otaz.marvelheroappsimple.data.models.JsonCharComResults
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults

@Database(
    entities = [JsonCharacterResults::class, JsonCharComResults::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
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
                "character_db.db"
            ).addMigrations(migration1to2).build()

        val migration1to2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS room_comic_results (name CHAR NOT NULL PRIMARY KEY)")
            }
        }
    }
}