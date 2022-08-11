package com.otaz.marvelheroappsimple.domain.model

import com.otaz.marvelheroappsimple.data.remote.dto.*

data class MarvelData(
    val `data`: Data,
)

data class DataData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int,
)

data class ResultData(
    val comics: Comics,
    val description: String,
    val id: Int,
    val name: String,
)

data class ThumbnailData(
    val extension: String,
    val path: String
)

data class ComicsData(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class ItemData(
    val name: String,
    val resourceURI: String
)