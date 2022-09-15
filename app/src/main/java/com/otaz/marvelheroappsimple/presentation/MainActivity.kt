package com.otaz.marvelheroappsimple.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.otaz.marvelheroappsimple.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(CharacterListFragment())
    }
    private fun replaceFragment(characterListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainLayout, characterListFragment).commit()
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
