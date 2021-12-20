package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.data.AppDatabase
import com.itis.android_homework.data.entity.Task
import com.itis.android_homework.databinding.TaskFragmentBinding
import com.itis.android_homework.fragments.date_picker.DatePickerFragment
import com.itis.android_homework.helpers.DateToStringConverter
import java.util.*

class TaskFragment : Fragment(R.layout.task_fragment) {

    private var binding: TaskFragmentBinding? = null
    private lateinit var database: AppDatabase
    private var calendar: Calendar? = null
    private var currentTaskId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TaskFragmentBinding.bind(view)
        database = AppDatabase.invoke(context) as AppDatabase

        binding?.apply {
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
                setNavigationOnClickListener {
                    (activity as? MainActivity)?.onBackPressed()
                }
                setOnMenuItemClickListener { onOptionsItemSelected(it) }
            }

            btnChooseDate.setOnClickListener {
                showDatePicker()
            }
        }

        checkIfTaskExists()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.icon_save -> {
                saveTask()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkIfTaskExists() {
        arguments?.getInt("ARG_TASK_ID")?.let {
            currentTaskId = it
            setTaskEditingView(it)
        }
    }

    private fun setTaskEditingView(id: Int) {
        val task = database.taskDao().getTaskById(id)
        binding?.apply {
            etTitle.setText(task?.title)
            multEtDesc.setText(task?.description)
            task?.date?.let {
                calendar = Calendar.getInstance()
                calendar?.time = it
                tvDate.text = DateToStringConverter.convertDateToString(it)
                tvDate.visibility = View.VISIBLE
//                tvLongitude.text = ...
//                tvLatitude.text = ...
            }
        }
    }

    private fun saveTask() {
        currentTaskId?.let{
            updateTask(it)
        }
        if (currentTaskId == null && isDataCorrect()) {
            addTask()
            showMessage("Successfully save.")
            returnToMainFragment()
        }
    }

    private fun updateTask(id: Int) {
        if(isDataCorrect()){
            refreshData(id)
            showMessage("Successfully updated.")
            returnToMainFragment()
        }
    }

    private fun refreshData(id: Int) {
        val task = database.taskDao().getTaskById(id)
        binding?.apply {
            task?.let {
                it.title = etTitle.text.toString()
                it.description = multEtDesc.text.toString()
                calendar?.apply {
                    it.date = time
                }
//                it.longitude = tvLongitude.text.toString().toDouble()
//                it.latitude = tvLatitude.text.toString().toDouble()
                database.taskDao().updateTask(task)
            }
        }
    }

    private fun addTask() {
        binding?.apply {
            val newTask = Task(
                0,
                etTitle.text.toString(),
                multEtDesc.text.toString(),
                calendar?.time,
                null,
                null
            )
            database.taskDao().save(newTask)
        }
    }

    private fun isDataCorrect(): Boolean {
        binding?.apply {
            if (etTitle.text.toString().isEmpty()) {
                showMessage("No title added.")
                return false
            }
            if (multEtDesc.text.toString().isEmpty()) {
                showMessage("No description added.")
                return false
            }
        }
        return true
    }

    private fun showDatePicker() {
        calendar = Calendar.getInstance()
        val datePickerFragment = DatePickerFragment()
        val supportFragmentManager = requireActivity().supportFragmentManager

        supportFragmentManager.setFragmentResultListener(
            "REQUEST_KEY",
            viewLifecycleOwner
        ) { resultKey, bundle ->
            if (resultKey == "REQUEST_KEY") {
                calendar?.timeInMillis = bundle.getLong("SELECTED_DATE")
                setDate(calendar)
            }
        }
        datePickerFragment.show(supportFragmentManager, "DatePickerDialog")
    }

    private fun setDate(calendar: Calendar?) {
        binding?.apply {
            tvDate.text = DateToStringConverter.convertDateToString(calendar?.time)
            tvDate.visibility = View.VISIBLE
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun returnToMainFragment() {
        (activity as? MainActivity)?.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
