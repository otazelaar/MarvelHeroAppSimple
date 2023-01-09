package com.otaz.marvelheroappsimple.network.responses

import com.google.gson.annotations.SerializedName
import com.otaz.marvelheroappsimple.network.model.MovieDto

data class MovieSearchResponse(
    @SerializedName("errorMessage")
    var errorMessage: String,
    @SerializedName("items")
    var movies: List<MovieDto>,
)