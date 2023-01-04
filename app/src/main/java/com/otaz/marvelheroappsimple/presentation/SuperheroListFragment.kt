package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.otaz.marvelheroappsimple.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperheroListFragment : Fragment(R.layout.fragment_superhero_list) {

    //    private lateinit var charactersAdapter: CharactersAdapter
//    private val viewModel: CharacterViewModel by viewModels()
//    private lateinit var characterRepository: CharacterRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        fun setUpRecyclerView() {
//            charactersAdapter = CharactersAdapter()
//            rvCharacterList.apply {
//                adapter = charactersAdapter
//                layoutManager = LinearLayoutManager(activity)
//            }
//        }
//        setUpRecyclerView()
//    }
    }
}

//
//
//        viewModel.characterList.observe(viewLifecycleOwner, Observer { response ->
//            when(response) {
//                is Resource.Success -> {
//                    hideProgressBar()
//                    response.data?.let { jsonCharacterRequest ->
//                        charactersAdapter.differ.submitList(jsonCharacterRequest.data?.results?.toList())
//                    }
//                }
//                is Resource.Error -> {
//                    hideProgressBar()
//                    response.message?.let { message ->
//                        Toast.makeText(activity, "An error occurred: $message", Toast.LENGTH_LONG).show()
//                    }
//                }
//                is Resource.Loading -> {
//                    showProgressBar()
//                }
//            }
//        })
//    }
//
//    private fun hideProgressBar() {
//        idProgressBar.visibility = View.INVISIBLE
//        isLoading = false
//    }
//
//    private fun showProgressBar() {
//        idProgressBar.visibility = View.VISIBLE
//        isLoading = true
//    }
//
//    var isLoading = false

