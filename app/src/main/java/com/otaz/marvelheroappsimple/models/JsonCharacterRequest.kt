package com.otaz.marvelheroappsimple.models

data class JsonCharacterRequest(
    val `data`: JsonCharacterData,
)

data class JsonCharacterData(
    val results: List<JsonCharacterResults>,
)

data class JsonCharacterResults(
    val id: Int,
    val name: String,
    val description: String,
    )
