package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.data.entity.Task
import com.itis.android_homework.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    private var binding: MainFragmentBinding? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.icon_delete_all -> {
                deleteAllTasks()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
    }

    private fun deleteAllTasks() {

    }

    private fun showTaskFragment(id: Int?) {
        var bundle: Bundle? = null
        id?.also {
            bundle = Bundle().apply {
                putInt("ARG_TASK_ID", id)
            }
        }
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()

        findNavController().navigate(
            R.id.action_mainFragment_to_taskFragment,
            bundle,
            options
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
