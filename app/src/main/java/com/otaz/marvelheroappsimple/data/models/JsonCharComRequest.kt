package com.otaz.marvelheroappsimple.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class JsonCharComRequest(
    val `data`: JsonCharComData,
)

data class JsonCharComData(
    val results: List<JsonCharComResults>,
)

@Entity(
    tableName = "room_comic_results"
)
data class JsonCharComResults(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
) : Serializable
