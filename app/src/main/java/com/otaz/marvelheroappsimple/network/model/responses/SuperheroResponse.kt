package com.otaz.marvelheroappsimple.network.model.responses

import com.google.gson.annotations.SerializedName
import com.otaz.marvelheroappsimple.network.model.SuperheroNetworkEntity
import java.io.Serializable

// Do I need a response mapper because my data class is nested further in my Json as opposed to CodingWithMitch's Json?
class SuperheroResponse (

    @SerializedName("data")
    val data: SuperheroData,
    @SerializedName("total")
    val total: Int,
) : Serializable

class SuperheroData(
    @SerializedName("results")
    val results: List<SuperheroNetworkEntity>,
) : Serializable