package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.db.CharacterDatabase
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import com.otaz.marvelheroappsimple.vm.CharacterViewModelProviderFactory
import kotlinx.android.synthetic.main.character_main.*

class CharacterActivity : AppCompatActivity() {

    lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_main)

        val characterRepository = CharacterRepository(CharacterDatabase(this))
        val viewModelProviderFactory = CharacterViewModelProviderFactory(application, characterRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(CharacterViewModel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.characterNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}