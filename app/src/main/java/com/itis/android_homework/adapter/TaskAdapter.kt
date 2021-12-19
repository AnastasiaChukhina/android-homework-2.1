package com.itis.android_homework.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.itis.android_homework.callbacks.TaskDiffItemCallback
import com.itis.android_homework.data.entity.Task

class TaskAdapter(
    private val action: (Int) -> Unit
): ListAdapter<Task, TaskHolder>(TaskDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskHolder = TaskHolder.create(parent, action)

    override fun onBindViewHolder(
        holder: TaskHolder,
        position: Int
    ) = holder.bind(getItem(position))

    override fun onBindViewHolder(
        holder: TaskHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else{
            payloads.last().takeIf { it is Bundle }?.let{
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun submitList(list: MutableList<Task>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}
