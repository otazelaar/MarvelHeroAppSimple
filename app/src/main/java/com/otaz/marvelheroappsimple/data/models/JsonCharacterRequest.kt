package com.otaz.marvelheroappsimple.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class JsonCharacterRequest(
    val `data`: JsonCharacterData,
)

data class JsonCharacterData(
    val results: List<JsonCharacterResults>,
)

@Entity(
    tableName = "room_character_results"
)
data class JsonCharacterResults(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
) : Serializable

data class Thumbnail(
    val extension: String,
    val path: String
)