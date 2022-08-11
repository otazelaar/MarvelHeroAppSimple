package com.otaz.marvelheroappsimple.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.api.APIService
import com.otaz.marvelheroappsimple.common.Constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.common.Constants.Companion.COMICS
import com.otaz.marvelheroappsimple.common.Constants.Companion.hash
import com.otaz.marvelheroappsimple.common.Constants.Companion.limit
import com.otaz.marvelheroappsimple.common.Constants.Companion.timeStamp
import com.otaz.marvelheroappsimple.data.remote.dto.Data
import com.otaz.marvelheroappsimple.data.remote.dto.MarvelDto
import com.otaz.marvelheroappsimple.data.remote.dto.Result
import com.otaz.marvelheroappsimple.databinding.ActivityMainBinding
import retrofit2.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.idHeroList
        binding.idProgressBar.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)

        APIService.api.getCharacters(1011334, COMICS ,limit, timeStamp, API_KEY, hash())
            .enqueue(object : Callback<MarvelDto> {
                override fun onFailure(call: Call<MarvelDto>, t: Throwable) {
                    binding.idProgressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<MarvelDto>, response: Response<MarvelDto>) {
                    recyclerView.adapter =
                        CharactersAdapter(response.body()!!.data.results, this@MainActivity)
                    binding.idProgressBar.visibility = View.GONE
                }

            })
    }
}