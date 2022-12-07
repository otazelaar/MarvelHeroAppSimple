package com.otaz.marvelheroappsimple.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedCharacterViewModel @Inject constructor(
    app: Application,
    private val characterRepository: CharacterRepository
) : AndroidViewModel(app) {

    fun saveCharacter(jsonCharacterResults: JsonCharacterResults) = viewModelScope.launch {
        characterRepository.upsert(jsonCharacterResults)
    }

    fun getSavedCharacters() = characterRepository.getSavedCharacters()

    fun deleteCharacter(jsonCharacterResults: JsonCharacterResults) = viewModelScope.launch {
        characterRepository.deleteCharacter(jsonCharacterResults)
    }
}