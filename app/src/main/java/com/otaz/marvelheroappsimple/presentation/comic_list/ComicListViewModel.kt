package com.otaz.marvelheroappsimple.presentation.comic_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaz.marvelheroappsimple.common.Constants.Companion.PARAM_CHAR_ID
import com.otaz.marvelheroappsimple.common.Resource
import com.otaz.marvelheroappsimple.domain.use_case.get_comics.GetComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ComicListViewModel @Inject constructor(
    private val getComicsUseCase: GetComicsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ComicListState())
    val state: State<ComicListState> = _state

    init {
        savedStateHandle.get<String>(PARAM_CHAR_ID)?.let { charID ->
            getComics(charID)}
    }

    private fun getComics(charID: String) {
        getComicsUseCase(charID).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ComicListState(comics = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ComicListState(error = result.message ?:
                    "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = ComicListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}