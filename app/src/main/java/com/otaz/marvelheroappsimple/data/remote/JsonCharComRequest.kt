package com.otaz.marvelheroappsimple.data.remote

data class JsonCharComRequest(
    val `data`: JsonCharComData,
)

data class JsonCharComData(
    val results: List<JsonCharComResults>,
)

data class JsonCharComResults(
    val id: Int,
    val title: String,
    val description: String,
)