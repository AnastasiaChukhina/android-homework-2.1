package com.itis.android_homework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.android_homework.entities.Dessert
import com.itis.android_homework.R
import com.itis.android_homework.databinding.ItemDessertBinding

class DessertHolder(
    private val binding: ItemDessertBinding,
    private val glide: RequestManager,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var dessert: Dessert? = null

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: Dessert) {
        this.dessert = item
        with(binding) {
            tvNameTitle.text = item.name
            tvHomeland.text = item.homeland

            tvNameTitle.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.cake_name
                )
            )

            glide.load(item.url)
                .apply(options)
                .into(ivImage)
        }

        itemView.setOnClickListener {
            action(item.id)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (Int) -> Unit
        ) = DessertHolder(
            ItemDessertBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, action
        )
    }
}

