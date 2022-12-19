package com.otaz.marvelheroappsimple.vm

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaz.marvelheroappsimple.data.models.JsonCharComRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.di.BaseApplication
import com.otaz.marvelheroappsimple.presentation.CharacterDetailFragmentArgs
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.utils.constants
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.QUERY_PAGE_SIZE
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val args = fromSavedStateHandle(savedStateHandle)

    private fun fromSavedStateHandle(handle: SavedStateHandle) =
        CharacterDetailFragmentArgs(
            charID = handle["charID"]!!,
        )

    val comicsByID: MutableLiveData<Resource<JsonCharComRequest>> = MutableLiveData()

    fun getComicsByID(charID: Int) = viewModelScope.launch {
        safeGetComicsByIDCall(charID)
    }

    private fun handleComicsByIDResponse(response: Response<JsonCharComRequest>): Resource<JsonCharComRequest> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveCharacter(jsonCharacterResults: JsonCharacterResults) = viewModelScope.launch {
        characterRepository.upsert(jsonCharacterResults)
    }

    private suspend fun safeGetComicsByIDCall(charID: Int) {
        val response = characterRepository.getComicsByID(charID, QUERY_PAGE_SIZE, TIMESTAMP, API_KEY, hash())
        comicsByID.postValue(handleComicsByIDResponse(response))
    }
}