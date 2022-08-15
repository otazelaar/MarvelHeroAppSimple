package com.otaz.marvelheroappsimple.domain.use_case.get_characters

import com.otaz.marvelheroappsimple.common.Resource
import com.otaz.marvelheroappsimple.data.remote.dto.CharacterRequestData
import com.otaz.marvelheroappsimple.data.remote.dto.CharacterRequestJson
import com.otaz.marvelheroappsimple.data.remote.dto.toCharacterRequestData
import com.otaz.marvelheroappsimple.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    operator fun invoke(): Flow<Resource<List<CharacterRequestData>>> = flow {
        try {
            emit(Resource.Loading())
            val characters = repository.getCharacters().map { it.toCharacterRequestData() }
            emit(Resource.Success(characters))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}