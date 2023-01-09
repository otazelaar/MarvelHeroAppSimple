package com.otaz.marvelheroappsimple.repository

import com.otaz.marvelheroappsimple.domain.model.Movie

interface MovieRepository{

    suspend fun search(apikey: String, query: String): List<Movie>

    suspend fun get(apikey: String, id: String): Movie
}