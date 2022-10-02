package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.repository.CharacterRepository
import com.otaz.marvelheroappsimple.db.CharacterDatabase
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import com.otaz.marvelheroappsimple.vm.CharacterViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterRepository = CharacterRepository(CharacterDatabase(this))
        val viewModelProviderFactory = CharacterViewModelProviderFactory(characterRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(CharacterViewModel::class.java)
        bottomNavigationView.setupWithNavController(characterNavHostFragment.findNavController())
    }
}