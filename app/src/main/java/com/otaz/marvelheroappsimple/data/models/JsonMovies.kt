package com.otaz.marvelheroappsimple.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JsonMovies(
    @SerializedName("errorMessage")
    val errorMessage: String,
    @SerializedName("items")
    val items: MutableList<Item>
) : Serializable