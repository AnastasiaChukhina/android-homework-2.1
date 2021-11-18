package com.itis.android_homework.adapter.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.android_homework.databinding.ItemRvFruitBinding
import com.itis.android_homework.models.Fruit

class FruitRVHolder(
    private val binding: ItemRvFruitBinding,
    private val glide: RequestManager,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var fruit: Fruit? = null

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: Fruit) {
        this.fruit = item
        with(binding) {
            tvName.text = item.name
            tvVitamins.text = item.vitamins

            glide.load(item.url[0])
                .apply(options)
                .into(ivImage)

            ivDelete.setOnClickListener {
                action(adapterPosition)
            }
        }
    }

    fun updateFields(bundle: Bundle) {
        bundle.run {
            getString("TITLE")?.also {
                updateName(it)
            }
            getString("VITAMINS")?.also {
                updateVitamins(it)
            }
        }
    }

    private fun updateName(name: String) {
        binding.tvName.text = name
    }

    private fun updateVitamins(vit: String) {
        binding.tvVitamins.text = vit
    }

    companion object {

        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (Int) -> Unit
        ) = FruitRVHolder(
            ItemRvFruitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, action
        )
    }
}
