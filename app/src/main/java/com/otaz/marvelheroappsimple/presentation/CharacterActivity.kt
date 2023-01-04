package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.di.BaseApplication
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import com.otaz.marvelheroappsimple.vm.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_main.*
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {

    @Inject
    lateinit var app: BaseApplication
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition() {
                viewModel.isLoading.value
            }
        }

        setContentView(R.layout.character_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.characterNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}