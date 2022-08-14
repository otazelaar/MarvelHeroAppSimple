package com.otaz.marvelheroappsimple.presentation.ui

sealed class Screen(val route: String) {
    object CharacterListScreen: Screen("character_list_screen")
    object ComicListScreen: Screen("comic_list_screen")
}
