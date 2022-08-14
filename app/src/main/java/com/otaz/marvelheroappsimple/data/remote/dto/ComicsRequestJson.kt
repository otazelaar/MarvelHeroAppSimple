package com.otaz.marvelheroappsimple.data.remote.dto

import com.otaz.marvelheroappsimple.domain.model.ComicsData

data class ComicsRequestJson(
    val `data`: ComicResultJson,
)

data class ComicResultJson(
    val results: List<ComicListJson>,
)

data class ComicListJson(
    val items: List<ComicJson>,
)

data class ComicJson(
    val name: String,
)

fun ComicJson.toComicData(): ComicData {
    return ComicData(
        name = name,
    )
}