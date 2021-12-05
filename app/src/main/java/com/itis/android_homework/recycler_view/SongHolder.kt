package com.itis.android_homework.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.android_homework.databinding.ItemSongBinding
import com.itis.android_homework.models.Song

class SongHolder(
    private val binding: ItemSongBinding,
    private val action: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Song) {
        with(binding) {
            tvSongName.text = item.title
            tvAuthor.text = item.author
            ivImage.setImageResource(item.cover)
        }
        itemView.setOnClickListener {
            action(item.id)
        }
    }

    companion object {
        fun create (
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = SongHolder (
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action)
    }
}
