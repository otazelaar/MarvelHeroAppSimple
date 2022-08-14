package com.otaz.marvelheroappsimple.presentation.comic_list

import com.otaz.marvelheroappsimple.domain.model.ComicsData
import com.otaz.marvelheroappsimple.domain.model.ItemData

data class ComicListState(
    val isLoading: Boolean = false,
    val comics: List<ItemData> = emptyList(),
    val error: String = ""
)