package com.otaz.marvelheroappsimple.domain.models

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)