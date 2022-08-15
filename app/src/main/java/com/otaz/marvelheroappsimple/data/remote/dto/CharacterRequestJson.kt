package com.otaz.marvelheroappsimple.data.remote.dto

data class CharacterRequestJson(
    val status: String,
)

fun CharacterRequestJson.toCharacterRequestData(): CharacterRequestData {
    return CharacterRequestData(
        status = status,
    )
}