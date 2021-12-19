package com.itis.android_homework.callbacks

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.itis.android_homework.data.entity.Task

class TaskDiffItemCallback: DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(
        oldItem: Task,
        newItem: Task
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: Task,
        newItem: Task
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Task, newItem: Task): Any? {
        val bundle = Bundle()

        if(oldItem.title != newItem.title){
            bundle.putString("TITLE", newItem.title)
        }
        if(oldItem.description != newItem.description){
            bundle.putString("DESCRIPTION", newItem.description)
        }
        if(oldItem.date != newItem.date){
            newItem.date?.let {
                bundle.putLong("DATE", it.time)
            }
        }

        if(bundle.isEmpty) return null
        return bundle
    }
}
