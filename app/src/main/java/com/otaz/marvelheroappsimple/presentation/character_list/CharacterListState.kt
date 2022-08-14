package com.otaz.marvelheroappsimple.presentation.character_list

import com.otaz.marvelheroappsimple.domain.model.ResultData

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<ResultData> = emptyList(),
    val error: String = ""
)