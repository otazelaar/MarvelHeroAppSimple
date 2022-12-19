package com.otaz.marvelheroappsimple.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaz.marvelheroappsimple.data.models.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.QUERY_PAGE_SIZE
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val characterList: MutableLiveData<Resource<JsonCharacterRequest>> = MutableLiveData()
    var characterListPage = 1
    var characterListResponse: JsonCharacterRequest? = null

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        safeCharactersCall()
    }

    private fun handleCharacterListResponse(response: Response<JsonCharacterRequest>): Resource<JsonCharacterRequest> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                characterListPage++
                if(characterListResponse == null) {
                    characterListResponse = resultResponse
                } else {
                    val oldCharacters = characterListResponse?.data?.results
                    val newCharacters = resultResponse.data.results
                    oldCharacters?.addAll(newCharacters)
                }
                return Resource.Success(characterListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeCharactersCall() {
        val response = characterRepository.getCharacters(QUERY_PAGE_SIZE, TIMESTAMP, API_KEY, hash())
        characterList.postValue(handleCharacterListResponse(response))
    }

}