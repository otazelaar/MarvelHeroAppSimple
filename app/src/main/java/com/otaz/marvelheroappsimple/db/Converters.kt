package com.otaz.marvelheroappsimple.db

import androidx.room.TypeConverter
import com.otaz.marvelheroappsimple.data.models.Thumbnail

class Converters {

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail) : String {
        return thumbnail.path
    }

    @TypeConverter
    fun toThumbnail(path: String) : Thumbnail {
        return Thumbnail(path, path)
    }
}