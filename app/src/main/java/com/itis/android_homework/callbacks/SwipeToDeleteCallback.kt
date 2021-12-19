package com.itis.android_homework.callbacks

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDeleteCallback(
    private val action: (Int) -> (Unit)
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        action(viewHolder.adapterPosition)
    }
}
