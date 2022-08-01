package com.otaz.marvelheroappsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.api.APIService
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.utils.constants
import com.otaz.marvelheroappsimple.models.Characters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.idHeroList)
        progressBar = findViewById(R.id.idProgressBar)
        progressBar.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)

        APIService.instance.getCharacters(limit = 100, constants.timeStamp, constants.API_KEY, constants.HASH)
            .enqueue(object : Callback<Characters> {
                override fun onFailure(call: Call<Characters>, t: Throwable) {
                    progressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                    recyclerView.adapter =
                        CharactersAdapter(response.body()!!.data.results, this@MainActivity)
                    progressBar.visibility = View.GONE
                }

            })
    }
}