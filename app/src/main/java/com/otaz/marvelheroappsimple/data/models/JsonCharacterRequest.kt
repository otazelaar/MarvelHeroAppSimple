package com.otaz.marvelheroappsimple.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class JsonCharacterRequest(
    val `data`: JsonCharacterData,
)

data class JsonCharacterData(
    val results: List<JsonCharacterResults>,
)

@Entity
data class JsonCharacterResults(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
)

