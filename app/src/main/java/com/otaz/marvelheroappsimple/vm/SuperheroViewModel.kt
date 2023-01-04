//package com.otaz.marvelheroappsimple.vm
//
//import android.app.Application
//import android.net.ConnectivityManager.*
//import android.net.NetworkCapabilities.*
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.otaz.marvelheroappsimple.data.CharacterResponse
//import com.otaz.marvelheroappsimple.data.models.CharacterResponse
//import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
//import com.otaz.marvelheroappsimple.utils.Resource
//import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
//import com.otaz.marvelheroappsimple.utils.constants.Companion.QUERY_PAGE_SIZE
//import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
//import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import retrofit2.Response
//import javax.inject.Inject
//
//@HiltViewModel
//class CharacterViewModel @Inject constructor(
//    app: Application,
//    private val characterRepository: CharacterRepository
//) : AndroidViewModel(app) {
//
//
//
//
//
//
////
////
////
////    val characterList: MutableLiveData<Resource<CharacterResponse>> = MutableLiveData()
////    var characterListPage = 1
////    var characterListResponse: CharacterResponse? = null
////
////    private fun getCharacters() = viewModelScope.launch {
////        val response = characterRepository.getCharacters(QUERY_PAGE_SIZE, TIMESTAMP, API_KEY, hash())
////        characterList.postValue(handleCharacterListResponse(response))
////    }
////
////
////    init { getCharacters() }
////
////    private fun handleCharacterListResponse(response: Response<CharacterResponse>): Resource<CharacterResponse> {
////        if(response.isSuccessful) {
////            response.body()?.let { resultResponse ->
////                characterListPage++
////                if(characterListResponse == null) {
////                    characterListResponse = resultResponse
////                } else {
////                    val oldCharacters = characterListResponse?.data?.results
////                    val newCharacters = resultResponse.data?.results
////                    if (newCharacters != null) {
////                        oldCharacters?.addAll(newCharacters)
////                    }
////                }
////                return Resource.Success(characterListResponse ?: resultResponse)
////            }
////        }
////        return Resource.Error(response.message())
////    }
//}