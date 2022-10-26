package com.otaz.marvelheroappsimple.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository

@Suppress("UNCHECKED_CAST")
class CharacterViewModelProviderFactory(
    val app: Application,
    private val characterRepository: CharacterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(app, characterRepository) as T
    }
}