package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.di.BaseApplication
import com.otaz.marvelheroappsimple.domain.model.SuperheroDomain
import com.otaz.marvelheroappsimple.network.model.RetrofitService
import com.otaz.marvelheroappsimple.network.model.SuperheroNetworkEntity
import com.otaz.marvelheroappsimple.network.model.SuperheroNetworkMapper
import com.otaz.marvelheroappsimple.utils.constants
import com.otaz.marvelheroappsimple.vm.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SuperheroActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofitService: RetrofitService

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

        val mapper = SuperheroNetworkMapper()
        val superheroDomain = SuperheroDomain()
        val networkEntity: SuperheroNetworkEntity = mapper.mapToEntity(superheroDomain)

        CoroutineScope(Dispatchers.IO).launch {
            val listOfCharacters = retrofitService.getListOfSuperheroes(
                limit = constants.QUERY_PAGE_SIZE,
                ts = constants.TIMESTAMP,
                apikey = constants.API_KEY,
                hash = constants.hash(),
            )

            Log.d("CharacterListFragment", "onViewCreated: ${listOfCharacters.data.results}")
        }

        setContentView(R.layout.character_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.characterNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}