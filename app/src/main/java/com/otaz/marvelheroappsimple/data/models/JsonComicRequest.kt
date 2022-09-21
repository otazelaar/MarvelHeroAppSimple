package com.otaz.marvelheroappsimple.data.models

data class JsonComicRequest(
    val `data`: JsonComicData,
)

data class JsonComicData(
    val results: List<JsonComicResults>,
)

data class JsonComicResults(
    val id: Int,
    val title: String,
    val issueNumber: Double,
    val description: String,
)
