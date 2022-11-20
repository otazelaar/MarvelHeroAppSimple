package com.otaz.marvelheroappsimple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.otaz.marvelheroappsimple.R
import com.otaz.marvelheroappsimple.data.models.JsonCharComResults
import kotlinx.android.synthetic.main.list_item_character.view.*
import kotlinx.android.synthetic.main.list_item_comic.view.*

class ComicsAdapter: RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    inner class ComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<JsonCharComResults>() {
        override fun areItemsTheSame(oldItem: JsonCharComResults, newItem: JsonCharComResults): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JsonCharComResults, newItem: JsonCharComResults): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_comic,
            parent, false
        )
        return ComicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comics = differ.currentList[position]
        holder.itemView.apply {
            tvComicName.text = comics.title
            setOnClickListener{
                onItemClickListener?.let { it(comics) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((JsonCharComResults) -> Unit)? = null

    fun setOnItemClickListener(listener: (JsonCharComResults) -> Unit) {
        onItemClickListener = listener
    }
}