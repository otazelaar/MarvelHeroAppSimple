package com.otaz.marvelheroappsimple.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaz.marvelheroappsimple.data.models.JsonCharComRequest
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
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val characterList: MutableLiveData<Resource<JsonCharacterRequest>> = MutableLiveData()
    var characterListPage = 1
    val searchCharacters: MutableLiveData<Resource<JsonCharacterRequest>> = MutableLiveData()
    var searchCharactersPage = 1
    val comicsByID: MutableLiveData<Resource<JsonCharComRequest>> = MutableLiveData()
    var comicsByIDPage = 1

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        characterList.postValue(Resource.Loading())
        val response = characterRepository.getCharacters(LIMIT, TIMESTAMP, API_KEY, hash())
        characterList.postValue(handleCharacterListResponse(response))
    }

    fun searchCharacters(nameStartsWith: String) = viewModelScope.launch {
        searchCharacters.postValue(Resource.Loading())
        val response = characterRepository.searchCharacters(nameStartsWith, LIMIT, TIMESTAMP, API_KEY, hash())
        searchCharacters.postValue(handleSearchCharactersResponse(response))
    }

    fun getComicsByID(charID: Int) = viewModelScope.launch {
        comicsByID.postValue(Resource.Loading())
        val response = characterRepository.getComicsByID(charID, LIMIT, TIMESTAMP, API_KEY, hash())
        comicsByID.postValue(handleComicsByIDResponse(response))
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
}