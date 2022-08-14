package com.otaz.marvelheroappsimple.presentation.comic_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.otaz.marvelheroappsimple.domain.model.ComicsData
import com.otaz.marvelheroappsimple.domain.model.ItemData

@Composable
fun ComicListItem(
    comics: ItemData,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = comics.name,
            style = MaterialTheme.typography.body1,
            overflow = Ellipsis
        )
    }
}