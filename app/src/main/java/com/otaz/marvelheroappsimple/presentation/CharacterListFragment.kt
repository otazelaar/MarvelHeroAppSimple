package com.otaz.marvelheroappsimple.presentation

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.api.APIService
import com.otaz.marvelheroappsimple.data.remote.JsonCharacterRequest
import com.otaz.marvelheroappsimple.data.remote.JsonCharacterResults
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment : Fragment(), CharactersAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var data: List<JsonCharacterResults>
    lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_character_list, container, false)
        val bt = v.findViewById<ImageButton>(R.id.idSearchButton)
        bt.setOnClickListener {
            val characterDetailFragment = CharacterDetailFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.mainLayout, characterDetailFragment)
            transaction.commit()
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvCharacterList)
        recyclerView.layoutManager = LinearLayoutManager(context)

        getMyData()
    }

    private fun getMyData() {
        APIService.instance.getCharacters(LIMIT, TIMESTAMP, API_KEY, hash())
            .enqueue(object : Callback<JsonCharacterRequest> {
                override fun onFailure(call: Call<JsonCharacterRequest>, t: Throwable) {
                    Log.e(TAG, "Unsuccessful Response")
                }

                override fun onResponse(call: Call<JsonCharacterRequest>, response: Response<JsonCharacterRequest>) {
                    val responseBody = response.body()!!

                    charactersAdapter = CharactersAdapter(
                        responseBody.data.results,
                        CharactersAdapter.OnItemClickListener,
                        this@CharacterListFragment
                    )

                    recyclerView.adapter = charactersAdapter

                    Log.i(TAG, "Successful Response")
                }
            })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = data[position]
        Log.i(TAG,"Item ${clickedItem.name} at position $position with id ${clickedItem.id} was clicked")
    }
}