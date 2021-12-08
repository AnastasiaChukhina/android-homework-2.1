package com.itis.android_homework.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.android_homework.models.Song

class SongAdapter(
    private val list: List<Song>,
    private val action: (Int) -> (Unit)
): RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongHolder = SongHolder.create(parent, action)

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
