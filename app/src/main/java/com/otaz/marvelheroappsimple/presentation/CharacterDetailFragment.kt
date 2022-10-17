package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.ComicsAdapter
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.list_item_character.*
import kotlinx.coroutines.launch

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    lateinit var viewModel: CharacterViewModel
    lateinit var comicsAdapter: ComicsAdapter
    val TAG = "CharacterDetailFragment"
    val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        val charID = args.charID

        tvCharacterTitleText.text = charID.name
        tvCharacterDescription.text = charID.description

        btSave.setOnClickListener {
            viewModel.saveCharacter(charID)
            Snackbar.make(view, "Character saved successfully", Snackbar.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            viewModel.getComicsByID(charID.id)
        }

        viewModel.comicsByID.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { jsonCharComRequest ->
                        comicsAdapter.differ.submitList(jsonCharComRequest.data.results)
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
        pbCharacterDetail.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        pbCharacterDetail.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        comicsAdapter = ComicsAdapter()
        rvCharComList.apply {
            adapter = comicsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}