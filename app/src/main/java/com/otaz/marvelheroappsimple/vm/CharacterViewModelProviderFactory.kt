package com.otaz.marvelheroappsimple.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository

class CharacterViewModelProviderFactory(
    private val characterRepository: CharacterRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(characterRepository) as T
    }
}