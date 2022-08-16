package com.otaz.marvelheroappsimple.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.remote.JsonComicResults
import com.otaz.marvelheroappsimple.viewholders.CharactersViewHolder

class ComicsAdapter(val data: List<JsonComicResults>, private val context: Context): RecyclerView.Adapter<CharactersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_characters_layout,
            parent, false
        )
        return CharactersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val comics = data[position]
        holder.characterName.text = comics.description
    }
}