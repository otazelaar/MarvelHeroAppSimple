package com.otaz.marvelheroappsimple.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otaz.marvelheroappsimple.R

class ComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val charComTitle: TextView = itemView.findViewById(R.id.tvCharComicTitle)
}

class ComicsVH(itemView: View) {
    val charComTitle: TextView = itemView.findViewById(R.id.idCharacterDescription)
}