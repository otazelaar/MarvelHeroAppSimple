package com.otaz.marvelheroappsimple.presentation

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.adapters.ComicsAdapter
import com.otaz.marvelheroappsimple.api.APIService
import com.otaz.marvelheroappsimple.data.remote.JsonCharComResults
import com.otaz.marvelheroappsimple.data.remote.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.remote.JsonCharacterResults
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvCharacterList)
        recyclerView.layoutManager = LinearLayoutManager(context)

        getRvData()
    }

    private fun getRvData() {
        APIService.instance.getCharacters(LIMIT, TIMESTAMP, API_KEY, hash())
            .enqueue(object : Callback<JsonCharacterRequest> {
                override fun onFailure(call: Call<JsonCharacterRequest>, t: Throwable) {
                    Log.e(TAG, "Unsuccessful Response")
                }

                override fun onResponse(call: Call<JsonCharacterRequest>, response: Response<JsonCharacterRequest>) {
                    response.body()?.let { responseBody ->
                        charactersAdapter =
                            CharactersAdapter(responseBody.data.results, this@CharacterListFragment)
                        setUpRecyclerAdapter(charactersAdapter)

                        Log.i(TAG, "Successful 'JsonCharacterRequest' Response")
                    }
                }
            })
    }

    fun setUpRecyclerAdapter(charactersAdapter: CharactersAdapter) {
        recyclerView.adapter = charactersAdapter

        charactersAdapter.onItemClick = { jsonCharacterResults ->
            // Passes character ID to CharacterDetailFragment so that it can be used to make API call
            val specificCharID = jsonCharacterResults.id
            val result = specificCharID.toString()
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))

            // Navigate to next fragment
            val characterDetailFragment = CharacterDetailFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.mainLayout, characterDetailFragment)
            transaction.commit()

            Log.d("TAG", "'${jsonCharacterResults.name}' was clicked with ID: ${jsonCharacterResults.id}")
            Toast.makeText(context, "You clicked '${jsonCharacterResults.name}' with ID: ${jsonCharacterResults.id}", Toast.LENGTH_SHORT).show()
        }
    }
}