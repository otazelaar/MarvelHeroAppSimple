package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.ComicsAdapter
import com.otaz.marvelheroappsimple.utils.Resource
import com.otaz.marvelheroappsimple.vm.CharacterDetailViewModel
import com.otaz.marvelheroappsimple.vm.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val viewModel: CharacterDetailViewModel by viewModels()
    lateinit var comicsAdapter: ComicsAdapter
    val args: CharacterDetailFragmentArgs by navArgs()
    val TAG = "CharacterDetailFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        val charID = args.charID
        val characterImagePath = charID.thumbnail.path

        tvCharacterTitleText.text = charID.name
        tvCharacterDescription.text = charID.description

        val characterImageUrl = "${characterImagePath}/landscape_incredible.jpg"
        Glide.with(this).load(characterImageUrl.toHttpsPrefix()).into(ivCharacterImageDetail)

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

    private fun String.toHttpsPrefix(): String? = if (isNotEmpty() && !startsWith("https://") && !startsWith("http://")) {
        "https://$this"
    } else if (startsWith("http://")) {
        replace("http://", "https://")
    } else this
}