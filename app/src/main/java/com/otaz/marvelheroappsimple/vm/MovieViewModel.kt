//package com.otaz.marvelheroappsimple.vm
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.otaz.marvelheroappsimple.utils.Resource
//import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import retrofit2.Response
//import javax.inject.Inject
//
//@HiltViewModel
//class MovieViewModel @Inject constructor(
//    private val characterRepository: CharacterRepository
//) : ViewModel() {
//
//    val characterList: MutableLiveData<Resource<JsonMovies>> = MutableLiveData()
//    var characterListPage = 1
//    var characterListResponse: JsonMovies? = null
//
//    private fun getMovies() = viewModelScope.launch {
//        val response = characterRepository.getMostPopularMovies(API_KEY)
//        characterList.postValue(handleCharacterListResponse(response))
//    }
//
//
//
//    init { getMovies() }
//
//    private fun handleCharacterListResponse(response: Response<JsonMovies>): Resource<JsonMovies> {
//        if(response.isSuccessful) {
//            response.body()?.let { resultResponse ->
//                characterListPage++
//                if(characterListResponse == null) {
//                    characterListResponse = resultResponse
//                } else {
//                    val oldCharacters = characterListResponse?.items
//                    val newCharacters = resultResponse.items
//                        oldCharacters?.addAll(newCharacters)
//                }
//                return Resource.Success(characterListResponse ?: resultResponse)
//            }
//        }
//        return Resource.Error(response.message())
//    }
//}
