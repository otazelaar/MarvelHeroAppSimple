package com.otaz.marvelheroappsimple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.models.JsonCharacterResults
import kotlinx.android.synthetic.main.list_item_character.view.*

class CharactersAdapter: RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    inner class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<JsonCharacterResults>() {
        override fun areItemsTheSame(oldItem: JsonCharacterResults, newItem: JsonCharacterResults): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JsonCharacterResults, newItem: JsonCharacterResults): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_character,
            parent, false
        )
        return CharactersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val characters = differ.currentList[position]
        holder.itemView.apply {
            tvCharacterName.text = characters.name
            setOnClickListener{
                onItemClickListener?.let { it(characters) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((JsonCharacterResults) -> Unit)? = null

    fun setOnItemClickListener(listener: (JsonCharacterResults) -> Unit) {
        onItemClickListener = listener
    }

}