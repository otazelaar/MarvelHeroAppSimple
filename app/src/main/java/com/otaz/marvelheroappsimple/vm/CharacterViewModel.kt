package com.otaz.marvelheroappsimple.vm

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.otaz.marvelheroappsimple.data.models.JsonCharComRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.utils.CharacterApplication
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class CharacterViewModel(
    app: Application,
    private val characterRepository: CharacterRepository
) : AndroidViewModel(app) {

    val characterList: MutableLiveData<Resource<JsonCharacterRequest>> = MutableLiveData()
    val searchCharacters: MutableLiveData<Resource<JsonCharacterRequest>> = MutableLiveData()
    val comicsByID: MutableLiveData<Resource<JsonCharComRequest>> = MutableLiveData()

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        safeCharactersCall()
    }

    fun searchCharacters(nameStartsWith: String) = viewModelScope.launch {
        safeSearchCharactersCall(nameStartsWith)
    }

    fun getComicsByID(charID: Int) = viewModelScope.launch {
        safeGetComicsByIDCall(charID)
    }

    private fun handleCharacterListResponse(response: Response<JsonCharacterRequest>): Resource<JsonCharacterRequest> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchCharactersResponse(response: Response<JsonCharacterRequest>): Resource<JsonCharacterRequest> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
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

    fun getSavedCharacters() = characterRepository.getSavedCharacters()

    fun deleteCharacter(jsonCharacterResults: JsonCharacterResults) = viewModelScope.launch {
        characterRepository.deleteCharacter(jsonCharacterResults)
    }

    private suspend fun safeCharactersCall() {
        characterList.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = characterRepository.getCharacters(LIMIT, TIMESTAMP, API_KEY, hash())
                characterList.postValue(handleCharacterListResponse(response))
            } else {
                characterList.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> characterList.postValue(Resource.Error("Network failure"))
                else -> characterList.postValue(Resource.Error("Conversion error"))
            }
        }
    }

    private suspend fun safeSearchCharactersCall(nameStartsWith: String) {
        searchCharacters.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = characterRepository.searchCharacters(nameStartsWith, LIMIT, TIMESTAMP, API_KEY, hash())
                searchCharacters.postValue(handleSearchCharactersResponse(response))
            } else {
                searchCharacters.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> searchCharacters.postValue(Resource.Error("Network failure"))
                else -> searchCharacters.postValue(Resource.Error("Conversion error"))
            }
        }
    }

    private suspend fun safeGetComicsByIDCall(charID: Int) {
        comicsByID.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = characterRepository.getComicsByID(charID, LIMIT, TIMESTAMP, API_KEY, hash())
                comicsByID.postValue(handleComicsByIDResponse(response))
            } else {
                comicsByID.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> comicsByID.postValue(Resource.Error("Network failure"))
                else -> comicsByID.postValue(Resource.Error("Conversion error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<CharacterApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return true
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}