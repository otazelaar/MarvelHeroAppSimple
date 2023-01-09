package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.otaz.marvelheroappsimple.di.BaseApplication
import com.otaz.marvelheroappsimple.network.MovieService
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.vm.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var app: BaseApplication
    private val viewModel: SplashScreenViewModel by viewModels()

    @Inject
    lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Image(
                painter = painterResource(id = com.otaz.marvelheroappsimple.R.drawable.ic_character_image),
                modifier = Modifier,
                alignment = Alignment.Center,
            )
        }


        installSplashScreen().apply {
            setKeepOnScreenCondition() {
                viewModel.isLoading.value
            }
        }

        CoroutineScope(IO).launch {
            val movie = movieService.get(
                apikey = API_KEY,
                id = "tt8946378"
            )
            Log.d("MainActivity", "onCreate ${movie.fullTitle}")
        }
    }
}