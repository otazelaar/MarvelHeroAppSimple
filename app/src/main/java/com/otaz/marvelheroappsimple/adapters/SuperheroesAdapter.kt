package com.otaz.marvelheroappsimple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.network.model.SuperheroNetworkEntity
import kotlinx.android.synthetic.main.list_item_character.view.*

class SuperheroesAdapter: RecyclerView.Adapter<SuperheroesAdapter.SuperheroesViewHolder>() {

    inner class SuperheroesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<SuperheroNetworkEntity>() {
        override fun areItemsTheSame(oldItem: SuperheroNetworkEntity, newItem: SuperheroNetworkEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SuperheroNetworkEntity, newItem: SuperheroNetworkEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_character,
            parent, false
        )
        return SuperheroesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SuperheroesViewHolder, position: Int) {
        val superheroes = differ.currentList[position]
        holder.itemView.apply {
            tvCharacterName.text = superheroes.name
//            val numberOfComics = superheroes.comics.available.toString()
//            tvCharacterNumberOfComics.text = "Number of comics: ${numberOfComics}"

//            val characterImageLink = "${superheroes.thumbnail.path}/standard_amazing.jpg"
//            Glide.with(this).load(characterImageLink.toHttpsPrefix()).into(ivCharacterImage)

            setOnClickListener{
                onItemClickListener?.let { it(superheroes) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((SuperheroNetworkEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (SuperheroNetworkEntity) -> Unit) {
        onItemClickListener = listener
    }

    private fun String.toHttpsPrefix(): String? = if (isNotEmpty() && !startsWith("https://") && !startsWith("http://")) {
        "https://$this"
    } else if (startsWith("http://")) {
        replace("http://", "https://")
    } else this
}