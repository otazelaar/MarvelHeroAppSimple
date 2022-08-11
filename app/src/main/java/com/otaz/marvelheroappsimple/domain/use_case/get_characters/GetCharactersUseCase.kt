package com.otaz.marvelheroappsimple.domain.use_case.get_characters

import com.otaz.marvelheroappsimple.common.Resource
import com.otaz.marvelheroappsimple.data.remote.dto.MarvelDto
import com.otaz.marvelheroappsimple.data.remote.dto.toMarvelData
import com.otaz.marvelheroappsimple.domain.model.MarvelData
import com.otaz.marvelheroappsimple.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    operator fun invoke(): Flow<Resource<List<MarvelData>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getCharacters().map { it.toMarvelData() }
            emit(Resource.Success(MarvelData))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {

        }
    }
}