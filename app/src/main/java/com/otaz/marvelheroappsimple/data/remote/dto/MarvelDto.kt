package com.otaz.marvelheroappsimple.data.remote.dto

import com.otaz.marvelheroappsimple.domain.model.MarvelData

data class MarvelDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)

fun MarvelDto.toMarvelData(): MarvelData {
    return MarvelData(
        data = data,
    )
}