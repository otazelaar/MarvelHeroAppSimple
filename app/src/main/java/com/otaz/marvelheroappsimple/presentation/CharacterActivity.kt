package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.db.CharacterDatabase
import com.otaz.marvelheroappsimple.di.BaseApplication
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_main.*
import javax.inject.Inject

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {

    @Inject
    lateinit var app: BaseApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.characterNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}