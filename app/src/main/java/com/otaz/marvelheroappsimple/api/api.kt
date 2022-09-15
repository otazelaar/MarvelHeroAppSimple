package com.otaz.marvelheroappsimple.api

import com.otaz.marvelheroappsimple.utils.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object api {
    val instance: Marvel

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        instance = retrofit.create(Marvel::class.java)
    }


}