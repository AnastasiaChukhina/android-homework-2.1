package com.itis.android_homework.adapter.cardView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.android_homework.models.Fruit

class FruitCVAdapter(
    private val list: List<Fruit>,
    private val glide: RequestManager
) : RecyclerView.Adapter<FruitCVHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FruitCVHolder = FruitCVHolder.create(parent, glide)


    override fun onBindViewHolder(
        holder: FruitCVHolder,
        position: Int
    ) = holder.bind(list[position])


    override fun getItemCount(): Int = list.size
}

