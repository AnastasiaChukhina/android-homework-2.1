package com.itis.android_homework.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.android_homework.entities.Dessert

class DessertAdapter(
    private val list: List<Dessert>,
    private val glide: RequestManager,
    private val action: (Int) -> Unit
) : RecyclerView.Adapter<DessertHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DessertHolder = DessertHolder.create(parent, glide, action)

    override fun onBindViewHolder(holder: DessertHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
