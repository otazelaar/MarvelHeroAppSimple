package com.otaz.marvelheroappsimple.domain.model

import java.io.Serializable

data class SuperheroDomain(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
//    val thumbnail: Thumbnail,
//    val comics: Comics,
) : Serializable