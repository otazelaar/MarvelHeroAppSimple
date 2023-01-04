package com.otaz.marvelheroappsimple.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class JsonCharacterRequest(
    val `data`: JsonCharacterData? = null,
    val total: Int? = null,
)

data class JsonCharacterData(
    val results: MutableList<JsonCharacterResults>? = null,
)

@Entity(
    tableName = "room_character_results"
)
data class JsonCharacterResults(
    @PrimaryKey
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val thumbnail: Thumbnail? = null,
    val comics: Comics? = null,
) : Serializable

data class Thumbnail(
    val extension: String? = null,
    val path: String? = null,
)

data class Comics(
    val available: Int? = null,
    val collectionURI: String? = null,
    val returned: Int? = null,
)