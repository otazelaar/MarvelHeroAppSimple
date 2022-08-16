package com.otaz.marvelheroappsimple.data.repository

data class JsonCharacterRequestRepository(
    val `data`: JsonCharacterDataRepository,
)

data class JsonCharacterDataRepository(
    val results: List<JsonCharacterResultsRepository>,
)

data class JsonCharacterResultsRepository(
    val id: Int,
    val name: String,
    val description: String,
)
