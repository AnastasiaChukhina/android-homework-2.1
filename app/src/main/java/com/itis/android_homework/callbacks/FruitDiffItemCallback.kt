package com.itis.android_homework.callbacks

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.itis.android_homework.models.Fruit

class FruitDiffItemCallback : DiffUtil.ItemCallback<Fruit>(){

    override fun areItemsTheSame(
        oldItem: Fruit,
        newItem: Fruit
    ): Boolean = oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: Fruit,
        newItem: Fruit
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Fruit, newItem: Fruit): Any? {
        val bundle = Bundle()

        if (oldItem.name != newItem.name) {
            bundle.putString("NAME", newItem.name)
        }
        if (oldItem.vitamins != newItem.vitamins) {
            bundle.putString("VITAMINS", newItem.vitamins)
        }

        if (bundle.isEmpty) return null
        return bundle
    }
}
