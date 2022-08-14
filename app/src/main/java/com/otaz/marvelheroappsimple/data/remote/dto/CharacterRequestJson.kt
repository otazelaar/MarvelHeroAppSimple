package com.otaz.marvelheroappsimple.data.remote.dto

data class CharactersRequestJson(
    val `data`: CharacterResultJson,
)

data class CharacterResultJson(
    val results: List<CharacterJson>,
)

data class CharacterJson(
    val name: String,
)

fun CharacterJson.toCharacterData(): CharacterData {
    return CharacterData(
        name = name,
    )
}