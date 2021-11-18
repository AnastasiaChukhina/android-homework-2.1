package com.itis.android_homework.adapter.cardView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.android_homework.databinding.ItemCvFruitBinding
import com.itis.android_homework.models.Fruit

class FruitCVHolder(
    private val binding: ItemCvFruitBinding,
    private val glide: RequestManager
) : RecyclerView.ViewHolder(binding.root) {

    private var fruit: Fruit? = null

    fun bind(item: Fruit) {
        this.fruit = item
        with(binding) {
            tvName.text = item.name
            tvVitamins.text = item.vitamins
            vp2Image.adapter = ViewPagerAdapter(item.url, glide)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            glide: RequestManager
        ) = FruitCVHolder(
            ItemCvFruitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide
        )
    }
}
