package com.otaz.marvelheroappsimple.api

import com.otaz.marvelheroappsimple.common.Constants.Companion.BASE_URL
import com.otaz.marvelheroappsimple.data.remote.MarvelApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    val api: MarvelApi by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
}