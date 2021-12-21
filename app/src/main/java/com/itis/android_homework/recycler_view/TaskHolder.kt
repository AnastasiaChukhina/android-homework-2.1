package com.itis.android_homework.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.android_homework.data.entity.Task
import com.itis.android_homework.databinding.TaskItemBinding
import com.itis.android_homework.helpers.DateToStringConverter

class TaskHolder(
    private val binding: TaskItemBinding,
    private val actionChoose: (Int) -> Unit,
    private val actionDelete: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var task: Task? = null

    fun bind(item: Task){
        this.task = item
        with(binding){
            tvTaskTitle.text = item.title
            tvTaskDate.text = DateToStringConverter.convertDateToString(item.date)

            ivDelete.setOnClickListener {
                actionDelete(item.id)
            }
            itemView.setOnClickListener {
                actionChoose(item.id)
            }
        }
    }

    fun updateFields(bundle: Bundle){
        bundle.run {
            getString("TITLE")?.also {
                updateTitle(it)
            }
            getString("DATE")?.also {
                updateDate(it)
            }
        }
    }

    private fun updateDate(date: String) {
        binding.tvTaskDate.text = date
    }

    private fun updateTitle(title: String) {
        binding.tvTaskTitle.text = title
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
