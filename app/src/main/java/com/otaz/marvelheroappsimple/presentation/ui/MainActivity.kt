package com.otaz.marvelheroappsimple.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.api.APIService
import com.otaz.marvelheroappsimple.common.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.common.constants.Companion.hash
import com.otaz.marvelheroappsimple.common.constants.Companion.limit
import com.otaz.marvelheroappsimple.common.constants.Companion.timeStamp
import com.otaz.marvelheroappsimple.databinding.ActivityMainBinding
import com.otaz.marvelheroappsimple.domain.models.Characters
import retrofit2.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BIG BUTT HOLE

        recyclerView = binding.idHeroList
        binding.idProgressBar.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)

        APIService.api.getCharacters(limit, timeStamp, API_KEY, hash())
            .enqueue(object : Callback<Characters> {
                override fun onFailure(call: Call<Characters>, t: Throwable) {
                    binding.idProgressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                    recyclerView.adapter =
                        CharactersAdapter(response.body()!!.data.results, this@MainActivity)
                    binding.idProgressBar.visibility = View.GONE
                }

            })
    }
}