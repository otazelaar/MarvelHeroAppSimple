package com.otaz.marvelheroappsimple.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SuperheroNetworkEntity(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
//    @SerializedName("thumbnail")
//    val thumbnail: Thumbnail,
//    @SerializedName("comics")
//    val comics: Comics,
) : Serializable

//data class Thumbnail(
//    val extension: String? = null,
//    val path: String? = null,
//)
//
//data class Comics(
//    val available: Int? = null,
//    val collectionURI: String? = null,
//    val returned: Int? = null,
//)