package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.utils.constants.Companion.SEARCH_CHARACTERS_TIME_DELAY
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_search_character.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchCharacterFragment : Fragment(R.layout.fragment_search_character) {

    lateinit var viewModel: CharacterViewModel
    lateinit var charactersAdapter: CharactersAdapter
    val TAG = "SearchCharacterFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        charactersAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("charID", it)
            }
            findNavController().navigate(R.id.action_searchCharacterFragment_to_characterDetailFragment,
                bundle
            )
        }

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_CHARACTERS_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.searchCharacters(editable.toString())
                    }
                }
            }
        }

        viewModel.searchCharacters.observe(viewLifecycleOwner, Observer { response ->
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
                        Log.e(TAG, "An error occurred: $message")
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
        rvSearchCharacters.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}