package com.itis.android_homework.adapter.recyclerView

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.itis.android_homework.callbacks.FruitDiffItemCallback
import com.itis.android_homework.models.Fruit

class FruitRVAdapter(
    private val glide: RequestManager,
    private val action: (Int) -> Unit
) :ListAdapter<Fruit, FruitRVHolder>(FruitDiffItemCallback()){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FruitRVHolder = FruitRVHolder.create(parent, glide, action)

    override fun onBindViewHolder(
        holder: FruitRVHolder,
        position: Int
    ) = holder.bind(getItem(position))

    override fun onBindViewHolder(
        holder: FruitRVHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.last().takeIf { it is Bundle }?.let {
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun submitList(list: MutableList<Fruit>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}
