package com.otaz.marvelheroappsimple.repository

import com.otaz.marvelheroappsimple.domain.model.Movie
import com.otaz.marvelheroappsimple.network.MovieService
import com.otaz.marvelheroappsimple.network.model.MovieDtoMapper

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val mapper: MovieDtoMapper,
): MovieRepository {
    override suspend fun search(apikey: String, query: String): List<Movie> {
        val result = movieService.search(apikey, query).movies
        return mapper.toDomainList(result)
    }

    override suspend fun get(apikey: String, id: String): Movie {
        val result = movieService.get(apikey, id)
        return mapper.mapToDomainModel(result)
    }
}