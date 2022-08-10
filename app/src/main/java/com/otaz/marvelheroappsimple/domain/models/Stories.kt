package com.otaz.marvelheroappsimple.domain.models

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)