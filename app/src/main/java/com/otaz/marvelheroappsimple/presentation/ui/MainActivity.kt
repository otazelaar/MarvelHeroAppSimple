package com.otaz.marvelheroappsimple.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.otaz.marvelheroappsimple.presentation.character_list.CharacterListScreen
import com.otaz.marvelheroappsimple.presentation.comic_list.ComicListScreen
import com.otaz.marvelheroappsimple.presentation.ui.theme.MarvelHeroAppSimpleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelHeroAppSimpleTheme{
                Surface(color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CharacterListScreen.route
                    ) {
                        composable(
                            route = Screen.CharacterListScreen.route
                        ) {
                            CharacterListScreen(navController)
                        }
                        composable(
                            route = Screen.ComicListScreen.route + "/{charID}"
                        ) {
                            ComicListScreen()
                        }
                    }
                }
            }
        }
    }
}