package com.otaz.marvelheroappsimple.data.local.entity

import androidx.room.Entity

@Entity
data class JsonCharacterResultsEntity(
    val id: Int,
    val name: String,
    val description: String,
)