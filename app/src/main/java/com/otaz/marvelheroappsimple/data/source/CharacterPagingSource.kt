package com.otaz.marvelheroappsimple.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.otaz.marvelheroappsimple.data.models.Comics
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.data.models.Thumbnail
import kotlin.math.max

const val STARTING_KEY = 0

class CharacterPagingSource : PagingSource<Int, JsonCharacterResults>() {

    // The load() function will be called by the Paging library
    // to asynchronously fetch more data to be displayed as the user scrolls around.
    // The LoadParams object keeps information related to the load operation, including the following:
    // Key of the page to be loaded & load size
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JsonCharacterResults> {
        // Start paging with the STARTING_KEY if this is the first load
        val start = params.key ?: STARTING_KEY
        // Load as many items as hinted by params.loadSize
        val range = start.until(start + params.loadSize)

        return LoadResult.Page(
            data = range.map { number ->
                JsonCharacterResults(
                    // Generate consecutive increasing numbers as the article id
                    id = number,
                    name = "Article $number",
                    description = "This describes article $number",
                    thumbnail = Thumbnail("",""),
                    comics = Comics(0,"",0)
                )
            },

            // Make sure we don't try to load items behind the STARTING_KEY
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, JsonCharacterResults>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}