package com.otaz.marvelheroappsimple.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel(
    val characterRepository: CharacterRepository
) : ViewModel() {

    val characterList: MutableLiveData<Resource<JsonCharacterRequest>> = MutableLiveData()
    val apiCallLimit = 99

    fun getBreakingNews() = viewModelScope.launch {
        characterList.postValue(Resource.Loading())
        val response = characterRepository.getCharacters(apiCallLimit, TIMESTAMP, API_KEY, hash())
        characterList.postValue(handleCharacterListResponse(response))
    }

    private fun handleCharacterListResponse(response: Response<JsonCharacterRequest>): Resource<JsonCharacterRequest> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}