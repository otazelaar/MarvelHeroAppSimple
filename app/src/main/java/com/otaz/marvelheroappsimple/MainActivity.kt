package com.otaz.marvelheroappsimple

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.adapters.CharactersAdapter
import com.otaz.marvelheroappsimple.api.APIService
import com.otaz.marvelheroappsimple.adapters.ComicsAdapter
import com.otaz.marvelheroappsimple.models.JsonCharComRequest
import com.otaz.marvelheroappsimple.models.JsonComicRequest
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_KEY
import com.otaz.marvelheroappsimple.utils.constants.Companion.CHARID
import com.otaz.marvelheroappsimple.utils.constants.Companion.LIMIT
import com.otaz.marvelheroappsimple.utils.constants.Companion.TIMESTAMP
import com.otaz.marvelheroappsimple.utils.constants.Companion.hash
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

        //API call for all comics
        APIService.instance.getComics(LIMIT, TIMESTAMP, API_KEY, hash())
            .enqueue(object : Callback<JsonComicRequest> {
                override fun onFailure(call: Call<JsonComicRequest>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Log.e(TAG, "Unsuccessful Response")
                }
                override fun onResponse(call: Call<JsonComicRequest>, response: Response<JsonComicRequest>) {
                    recyclerView.adapter =
                        ComicsAdapter(response.body()!!.data.results, this@MainActivity)
                    Log.e(TAG, "Successful Response")
                }
            })
    }
}

        // API call for character list of comics
//        APIService.instance.getComicsByID(charID = CHARID, LIMIT, TIMESTAMP, API_KEY, hash())
//            .enqueue(object : Callback<JsonCharComRequest> {
//                override fun onFailure(call: Call<JsonCharComRequest>, t: Throwable) {
//                    progressBar.visibility = View.GONE
//                    Log.e(TAG, "Unsuccessful Response")
//                }
//
//                override fun onResponse(
//                    call: Call<JsonCharComRequest>, response: Response<JsonCharComRequest>
//                ) {
//                    recyclerView.adapter =
//                        ComicsAdapter(response.body()!!.data.results, this@MainActivity)
//                    progressBar.visibility = View.GONE
//                    Log.e(TAG, "Successful Response")
//
//                }
//            })



//         API call for character ID so that it can be used to retrieve a specific character's comics
