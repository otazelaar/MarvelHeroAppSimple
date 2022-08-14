package com.otaz.marvelheroappsimple.domain.use_case.get_comics

import com.otaz.marvelheroappsimple.common.Resource
import com.otaz.marvelheroappsimple.data.remote.dto.toComicsData
import com.otaz.marvelheroappsimple.data.remote.dto.toItemsData
import com.otaz.marvelheroappsimple.data.remote.dto.toResultData
import com.otaz.marvelheroappsimple.domain.model.ComicsData
import com.otaz.marvelheroappsimple.domain.model.ItemData
import com.otaz.marvelheroappsimple.domain.model.ResultData
import com.otaz.marvelheroappsimple.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    operator fun invoke(charID: String): Flow<Resource<List<ItemData>>> = flow {
        try {
            emit(Resource.Loading())
            val comics = repository.getComicsByID(charID).map { it.toItemsData() }
            emit(Resource.Success(comics))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}