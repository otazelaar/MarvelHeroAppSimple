package com.otaz.marvelheroappsimple.presentation

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.adapters.ComicsAdapter
import com.otaz.marvelheroappsimple.api.APIService
import com.otaz.marvelheroappsimple.data.remote.JsonCharComRequest
import com.otaz.marvelheroappsimple.data.remote.JsonCharComResults
import com.otaz.marvelheroappsimple.data.remote.JsonCharacterResults
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.CHARID
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var comicsAdapter: ComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvCharComList)
        recyclerView.layoutManager = LinearLayoutManager(context)

        getRvData()
    }

    private fun getRvData() {
        val specificCharID = 1017100
        CHARID = specificCharID

        APIService.instance.getComicsByID(CHARID, LIMIT, TIMESTAMP, API_KEY, hash())
            .enqueue(object : Callback<JsonCharComRequest> {
                override fun onFailure(call: Call<JsonCharComRequest>, t: Throwable) {
                    Log.e(TAG, "Unsuccessful 'JsonCharComRequest' Response")
                }

                override fun onResponse(call: Call<JsonCharComRequest>, response: Response<JsonCharComRequest>) {
                    response.body()?.let { responseBody ->
                        comicsAdapter =
                            ComicsAdapter(responseBody.data.results, this@CharacterDetailFragment)
                        setUpRecyclerAdapter(comicsAdapter)

                        Log.i(TAG, "Successful 'JsonCharComRequest' Response")
                    }
                }
            })
    }

    fun setUpRecyclerAdapter(comicsAdapter: ComicsAdapter) {
        recyclerView.adapter = comicsAdapter
    }
}