package com.itis.android_homework.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.android_homework.databinding.ItemSongBinding
import com.itis.android_homework.models.Song

class SongHolder(
    private val binding: ItemSongBinding,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Song) {
        with(binding) {
            tvSongName.text = item.title
            tvAuthor.text = item.author
            tvSongDuration.text = setSongDuration(item.duration)
            ivImage.setImageResource(item.cover)
        }
        itemView.setOnClickListener {
            action(item.id)
        }
    }

    private fun setSongDuration(duration: Int): String {
        val min = (duration / 60).toString()
        val sec = (duration % 60).toString()
        return if(sec.length == 1) "${min}:0${sec}"
                else "${min}:${sec}"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = SongHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action
        )
    }
}
