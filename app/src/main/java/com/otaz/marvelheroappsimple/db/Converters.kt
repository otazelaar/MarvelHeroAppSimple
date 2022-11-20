package com.otaz.marvelheroappsimple.db

import androidx.room.TypeConverter
import com.otaz.marvelheroappsimple.data.models.Comics
import com.otaz.marvelheroappsimple.data.models.Thumbnail

class Converters {

    // Converters for Thumbnail
    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail) : String {
        return thumbnail.path
    }

    @TypeConverter
    fun toThumbnail(path: String) : Thumbnail {
        return Thumbnail(path, path)
    }

    // Converters for Comics
    @TypeConverter
    fun fromComics(comics: Comics) : Int {
        return comics.available
    }

    @TypeConverter
    fun toComics(available: Int) : Comics {
        return Comics(available, available.toString(), available)
    }
}