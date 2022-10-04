package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.fragment_saved_character.*

class SavedCharacterFragment : Fragment(R.layout.fragment_saved_character) {

    lateinit var viewModel: CharacterViewModel
    lateinit var charactersAdapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        charactersAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("character", it)
            }
            findNavController().navigate(R.id.action_savedCharacterFragment_to_characterDetailFragment,
                bundle
            )
        }
    }

    private fun setUpRecyclerView() {
        charactersAdapter = CharactersAdapter()
        rvSavedCharacters.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}