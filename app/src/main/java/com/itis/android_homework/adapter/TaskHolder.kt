package com.itis.android_homework.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.android_homework.data.entity.Task
import com.itis.android_homework.databinding.TaskItemBinding

class TaskHolder(
    private val binding: TaskItemBinding,
    private val actionChoose: (Int) -> Unit,
    private val actionDelete: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var task: Task? = null

    fun bind(item: Task){
        this.task = item
        with(binding){

        }
    }

    fun updateFields(bundle: Bundle){

    }

    companion object{

        fun create(
            parent:ViewGroup,
            actionChoose: (Int) -> Unit,
            actionDelete: (Int) -> Unit
        ) = TaskHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), actionChoose, actionDelete
        )
    }
}
