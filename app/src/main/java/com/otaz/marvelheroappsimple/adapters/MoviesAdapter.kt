package com.otaz.marvelheroappsimple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.models.Item
import com.otaz.marvelheroappsimple.data.models.JsonMovies
import kotlinx.android.synthetic.main.list_item_character.view.*

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_character,
            parent, false
        )
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = differ.currentList[position]
        holder.itemView.apply {
            tvCharacterName.text = movies.fullTitle
            val imdbRating = movies.imDbRating
            tvCharacterNumberOfComics.text = "Number of comics: ${imdbRating}"

            setOnClickListener{
                onItemClickListener?.let { it(movies) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Item) -> Unit)? = null

    fun setOnItemClickListener(listener: (Item) -> Unit) {
        onItemClickListener = listener
    }

    private fun String.toHttpsPrefix(): String? = if (isNotEmpty() && !startsWith("https://") && !startsWith("http://")) {
        "https://$this"
    } else if (startsWith("http://")) {
        replace("http://", "https://")
    } else this
}