package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private lateinit var charactersAdapter: CharactersAdapter
    lateinit var viewModel: CharacterViewModel
    val TAG = "CharacterListFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CharacterActivity).viewModel
        setUpRecyclerView()

        charactersAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("charID", it)
            }
            findNavController().navigate(R.id.action_characterListFragment_to_characterDetailFragment,
            bundle
            )
        }

        viewModel.characterList.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { jsonCharacterRequest ->
                        charactersAdapter.differ.submitList(jsonCharacterRequest.data.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occurred: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        idProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        idProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        charactersAdapter = CharactersAdapter()
        rvCharacterList.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}