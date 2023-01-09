package com.otaz.marvelheroappsimple.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Movie (
    val crew: String? = null,
    val fullTitle: String? = null,
    val id: String? = null,
    val imDbRating: String? = null,
    val imDbRatingCount: String? = null,
    val image: String? = null,
    val rank: String? = null,
    val title: String? = null,
    val year: String? = null,
) : Parcelable