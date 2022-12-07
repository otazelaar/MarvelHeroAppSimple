package com.otaz.marvelheroappsimple.vm

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.otaz.marvelheroappsimple.data.models.JsonCharComRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.di.BaseApplication
import com.otaz.marvelheroappsimple.presentation.CharacterDetailFragmentArgs
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.utils.constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    app: Application,
    private val characterRepository: CharacterRepository,
) : AndroidViewModel(app) {

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
        comicsByID.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = characterRepository.getComicsByID(charID,
                    constants.QUERY_PAGE_SIZE,
                    constants.TIMESTAMP,
                    constants.API_KEY,
                    constants.hash()
                )
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
        val connectivityManager = getApplication<BaseApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return true
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}