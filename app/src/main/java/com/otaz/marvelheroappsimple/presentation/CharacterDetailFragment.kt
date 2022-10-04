package com.otaz.marvelheroappsimple.presentation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.adapters.ComicsAdapter
import com.otaz.marvelheroappsimple.api.RetrofitInstance
import com.otaz.marvelheroappsimple.data.models.JsonCharComRequest
import com.otaz.marvelheroappsimple.data.models.JsonCharComResults
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.fragment_search_character.*
import kotlinx.android.synthetic.main.fragment_search_character.idProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    lateinit var viewModel: CharacterViewModel
    lateinit var comicsAdapter: ComicsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        viewModel.comicsByID.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { jsonCharacterRequest ->
                        comicsAdapter.differ.submitList(jsonCharacterRequest.data.results)
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
        comicsAdapter = ComicsAdapter()
        rvCharComList.apply {
            adapter = comicsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}