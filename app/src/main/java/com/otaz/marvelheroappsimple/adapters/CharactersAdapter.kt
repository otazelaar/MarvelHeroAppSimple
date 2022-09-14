package com.otaz.marvelheroappsimple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.remote.JsonCharacterResults
import com.otaz.marvelheroappsimple.presentation.CharacterListFragment

/**
 * Originally, there was a viewholder but it was merged with the adapter.
 * The comics Adapter has not yet been merged with its respective viewholder.
 * This is just to serve as a reminder to merge those at some point.
 *
 */

class CharactersAdapter(
    val data: List<JsonCharacterResults>
):
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    var onItemClick: ((JsonCharacterResults) -> Unit)? = null
    var jsonCharacterResults: List<JsonCharacterResults> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_character,
            parent, false
        )
        return CharactersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val characters = data[position]
        holder.characterName.text = characters.name
    }

    override fun getItemCount() = data.size

    inner class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val characterName: TextView = itemView.findViewById(R.id.tvCharacterName)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(jsonCharacterResults[adapterPosition])
            }
        }
    }
}