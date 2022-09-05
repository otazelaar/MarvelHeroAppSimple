package com.otaz.marvelheroappsimple.presentation

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.ComicsAdapter
import com.otaz.marvelheroappsimple.api.APIService
import com.otaz.marvelheroappsimple.data.remote.JsonCharComRequest
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.CHARID
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var recyclerView: RecyclerView

class CharacterDetailFragment : Fragment() {

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
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvCharComList)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        CHARID = 1017100

        APIService.instance.getComicsByID(CHARID, LIMIT, TIMESTAMP, API_KEY, hash())
            .enqueue(object : Callback<JsonCharComRequest> {
                override fun onFailure(call: Call<JsonCharComRequest>, t: Throwable) {
                    Log.e(ContentValues.TAG, "Unsuccessful 'JsonComicRequest' Response")
                }

                override fun onResponse(call: Call<JsonCharComRequest>, response: Response<JsonCharComRequest>) {
                    recyclerView.adapter =
                        ComicsAdapter(response.body()!!.data.results, this@CharacterDetailFragment)
                    Log.i(ContentValues.TAG, "Successful 'JsonComicRequest' Response")
                }
            })

        APIService.instance.getComicsByID(CHARID, LIMIT, TIMESTAMP, API_KEY, hash())
            .enqueue(object : Callback<JsonCharComRequest> {
                override fun onFailure(call: Call<JsonCharComRequest>, t: Throwable) {
                    Log.e(ContentValues.TAG, "Unsuccessful 'JsonComicRequest' Response")
                }

                override fun onResponse(call: Call<JsonCharComRequest>, response: Response<JsonCharComRequest>) {
                    recyclerView.adapter =
                        ComicsAdapter(response.body()!!.data.results, this@CharacterDetailFragment)
                    Log.i(ContentValues.TAG, "Successful 'JsonComicRequest' Response")
                }
            })
    }
}