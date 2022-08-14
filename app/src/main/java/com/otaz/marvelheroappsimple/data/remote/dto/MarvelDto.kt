package com.otaz.marvelheroappsimple.data.remote.dto

import com.otaz.marvelheroappsimple.domain.model.ComicsData
import com.otaz.marvelheroappsimple.domain.model.ItemData
import com.otaz.marvelheroappsimple.domain.model.MarvelData
import com.otaz.marvelheroappsimple.domain.model.ResultData

data class MarvelDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)

fun MarvelDto.toMarvelData(): MarvelData {
    return MarvelData(
        data = data,
    )
}

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)

data class Result(
    val comics: Comics,
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)

fun Result.toResultData(): ResultData {
    return ResultData(
        comics = comics,
        description = description,
        id = id,
        name = name,
    )
}

data class Thumbnail(
    val extension: String,
    val path: String
)

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

fun Comics.toComicsData(): ComicsData {
    return ComicsData(
        available = available,
        collectionURI = collectionURI,
        items = items,
        returned = returned,
    )
}

data class Item(
    val name: String,
    val resourceURI: String
)

fun Item.toItemsData(): ItemData {
    return ItemData(
        name = name,
        resourceURI = resourceURI,
    )
}

data class ItemXXX(
    val name: String,
    val resourceURI: String,
    val type: String
)

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)

data class Url(
    val type: String,
    val url: String
)

/**
data class CharactersRequestJson(
val data: CharacterResultJson,
)

data class CharacterResultJson(
val characters: List<CharacterJson>,
)

data class CharacterJson(
val comics: List<ComicJson>,
)

data class ComicJson(
val resourceUri: String,
val name: String,
)
 **/