package com.otaz.marvelheroappsimple.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.databinding.ItemCharactersLayoutBinding
import com.otaz.marvelheroappsimple.domain.models.Characters
import com.otaz.marvelheroappsimple.domain.models.Result
import retrofit2.Callback

/**
 * Binds Character data object to a ViewHolder
 */
class CharactersAdapter(var data: List<Result>, private val context: Context): RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    inner class CharactersViewHolder(val binding: ItemCharactersLayoutBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(ItemCharactersLayoutBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(viewHolder: CharactersViewHolder, position: Int) {
        viewHolder.binding.apply {
            val characters = data[position]
            characterName.text = characters.name
        }
    }

    override fun getItemCount() = data.size
}