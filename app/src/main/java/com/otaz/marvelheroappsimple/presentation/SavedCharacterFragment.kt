package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.vm.CharacterListViewModel
import com.otaz.marvelheroappsimple.vm.SavedCharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_saved_character.*

@AndroidEntryPoint
class SavedCharacterFragment : Fragment(R.layout.fragment_saved_character) {

    private val viewModel: SavedCharacterViewModel by viewModels()
    lateinit var charactersAdapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        charactersAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("charID", it)
            }
            findNavController().navigate(
                R.id.action_savedCharacterFragment_to_characterDetailFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val character = charactersAdapter.differ.currentList[position]
                viewModel.deleteCharacter(character)
                Snackbar.make(view, "Successfully deleted character", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveCharacter(character)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedCharacters)
        }

        viewModel.getSavedCharacters().observe(viewLifecycleOwner, Observer { characters ->
            charactersAdapter.differ.submitList(characters)
        })
    }

    private fun setUpRecyclerView() {
        charactersAdapter = CharactersAdapter()
        rvSavedCharacters.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}