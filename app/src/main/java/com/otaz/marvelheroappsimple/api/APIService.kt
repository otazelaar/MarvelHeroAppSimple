package com.otaz.marvelheroappsimple.api

import com.otaz.marvelheroappsimple.common.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    val api: Marvel by lazy{
        Retrofit.Builder()
            .baseUrl(constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Marvel::class.java)
    }
}