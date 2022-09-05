package com.otaz.marvelheroappsimple.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.remote.JsonCharComResults
import com.otaz.marvelheroappsimple.presentation.CharacterDetailFragment
import com.otaz.marvelheroappsimple.viewholders.ComicsViewHolder

class ComicsAdapter(val data: List<JsonCharComResults>, private val context: CharacterDetailFragment): RecyclerView.Adapter<ComicsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_comic,
            parent, false
        )
        return ComicsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comics = data[position]
        holder.charComTitle.text = comics.title
    }
}