package com.otaz.marvelheroappsimple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.remote.JsonCharComResults
import com.otaz.marvelheroappsimple.presentation.CharacterDetailFragment

class ComicsAdapter(
    val data: List<JsonCharComResults>,
):
    RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_comic,
            parent, false
        )
        return ComicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comics = data[position]
        holder.charComTitle.text = comics.title
    }

    override fun getItemCount() = data.size

    inner class ComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val charComTitle: TextView = itemView.findViewById(R.id.tvCharComicTitle)
    }
}