package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.MoviesAdapter
import com.otaz.marvelheroappsimple.api.ApiService
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.list_item_character.*
import kotlinx.android.synthetic.main.list_item_character.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: CharacterViewModel by viewModels()

    @Inject
    lateinit var apiService: ApiService

    val TAG = "CharacterListFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        CoroutineScope(Dispatchers.IO).launch {
            val service = apiService
            val response = service.getMostPopularMovies(
                apikey = API_KEY
            )
            Log.d("CharacterListFragment", "onViewCreate $response")
        }

        viewModel.characterList.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { Movie ->
                        moviesAdapter.differ.submitList(Movie.items)
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
        isLoading = false
    }

    private fun showProgressBar() {
        idProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false

    private fun setUpRecyclerView() {
        moviesAdapter = MoviesAdapter()
        rvCharacterList.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}