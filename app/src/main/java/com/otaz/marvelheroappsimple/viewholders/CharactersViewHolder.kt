package com.otaz.marvelheroappsimple.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R

class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val characterName: TextView = itemView.findViewById(R.id.characterName)
    val characterImage: ImageView = itemView.findViewById(R.id.characterImage)

}