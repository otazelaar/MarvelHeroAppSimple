package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.vm.CharacterViewModel

class SavedCharacterFragment : Fragment(R.layout.fragment_saved_character) {

    lateinit var viewModel: CharacterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

    }
}