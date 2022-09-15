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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.ComicsAdapter
import com.otaz.marvelheroappsimple.api.api
import com.otaz.marvelheroappsimple.data.remote.JsonCharComRequest
import com.otaz.marvelheroappsimple.data.remote.JsonCharComResults
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var comicsAdapter: ComicsAdapter
    lateinit var  data: List<JsonCharComResults>

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

        setFragmentListener()
    }

    private fun getRvData(specificCharID: Int) {
        api.instance.getComicsByID(specificCharID, LIMIT, TIMESTAMP, API_KEY, hash())
            .enqueue(object : Callback<JsonCharComRequest> {
                override fun onFailure(call: Call<JsonCharComRequest>, t: Throwable) {
                    Log.e(TAG, "Unsuccessful 'JsonCharComRequest' Response")
                }

                override fun onResponse(call: Call<JsonCharComRequest>, response: Response<JsonCharComRequest>) {
                    val characterDetail = response.body()
                    characterDetail?.let { responseBody ->
                        comicsAdapter =
                            ComicsAdapter(responseBody.data.results)
                        setUpRecyclerAdapter(comicsAdapter)

                        val tvCharacterDescription = view?.findViewById<TextView>(R.id.tvCharacterDescription)
                        val results = responseBody.data.results[specificCharID]
                        results.let {
                            tvCharacterDescription?.let { it.text = results.description }
                            // by adding [specificCharID] above, I get access to be able to write results.description
                            // This makes the app crash and I am not sure why.
                            // I can't seem to find another way to be able to access the different api results such as...
                            // id, description, title, etc.
                        }
                        Log.i(TAG, "Successful 'JsonCharComRequest' Response")
                    }
                }
            })
    }

    fun setUpRecyclerAdapter(comicsAdapter: ComicsAdapter) {
        recyclerView.adapter = comicsAdapter
    }

    private fun setFragmentListener() {
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            var result = bundle.getString("bundleKey")
            getRvData(result!!.toInt())
        }
    }
}