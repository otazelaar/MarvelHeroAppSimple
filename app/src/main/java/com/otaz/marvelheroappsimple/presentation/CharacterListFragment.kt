package com.otaz.marvelheroappsimple.presentation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.api.RetrofitInstance
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import com.otaz.marvelheroappsimple.vm.CharacterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var viewModel: CharacterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        recyclerView = view.findViewById(R.id.rvCharacterList)
        recyclerView.layoutManager = LinearLayoutManager(context)

        crGetRvData()
    }

    private fun crGetRvData() {
        lifecycleScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getCharacters(LIMIT, TIMESTAMP, API_KEY, hash())
            if(response.isSuccessful) {
                for(characters in response.body()!!.data.results) {
                    response.body()?.let { responseBody ->
                        charactersAdapter =
                            CharactersAdapter(responseBody.data.results)
                        recyclerView.adapter = charactersAdapter

                        Log.i(TAG, "Successful 'JsonCharacterRequest' Response")
                    }
                }
            }
        }
    }
}