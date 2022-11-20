package com.otaz.marvelheroappsimple.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class JsonCharacterRequest(
    val `data`: JsonCharacterData,
    val total: Int?,
)

data class JsonCharacterData(
    val results: MutableList<JsonCharacterResults>,
)

@Entity(
    tableName = "room_character_results"
)
data class JsonCharacterResults(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail,
    val comics: Comics,
) : Serializable

data class Thumbnail(
    val extension: String,
    val path: String
)

data class Comics(
    val available: Int,
    val collectionURI: String,
    val returned: Int
)