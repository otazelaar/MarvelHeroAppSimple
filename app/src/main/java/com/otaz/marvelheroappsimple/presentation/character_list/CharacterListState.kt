package com.otaz.marvelheroappsimple.presentation.character_list

import com.otaz.marvelheroappsimple.data.remote.dto.CharacterRequestData

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<CharacterRequestData> = emptyList(),
    val error: String = ""
)