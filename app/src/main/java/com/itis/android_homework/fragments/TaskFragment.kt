package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.databinding.TaskFragmentBinding

class TaskFragment : Fragment(R.layout.task_fragment) {

    private var binding: TaskFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TaskFragmentBinding.bind(view)

        setOnBackPressed()
    }

    private fun setOnBackPressed(){
        binding?.toolbar?.apply{
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                (activity as? MainActivity)?.onBackPressed()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.icon_save -> {
                saveTask()
                true
            }
            android.R.id.home -> {
                (activity as? MainActivity)?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveTask(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
