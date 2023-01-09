package com.otaz.marvelheroappsimple.presentation.movie_list

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val randomString: String
): ViewModel(){

    init {
        println("VIEWMODEL: $randomString")
    }
}