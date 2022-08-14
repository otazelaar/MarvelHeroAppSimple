package com.otaz.marvelheroappsimple.domain.use_case.get_characters

import com.otaz.marvelheroappsimple.common.Resource
import com.otaz.marvelheroappsimple.data.remote.dto.Result
import com.otaz.marvelheroappsimple.data.remote.dto.toResultData
import com.otaz.marvelheroappsimple.domain.model.ResultData
import com.otaz.marvelheroappsimple.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    operator fun invoke(): Flow<Resource<ResultData>> = flow {
        try {
            emit(Resource.Loading<ResultData>())
            val characters = repository.getCharacters().map { it.toResultData() }
            emit(Resource.Success<ResultData>(characters))
        } catch (e: HttpException) {
            emit(Resource.Error<ResultData>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<ResultData>("Couldn't reach server. Check your internet connection"))
        }
    }
}