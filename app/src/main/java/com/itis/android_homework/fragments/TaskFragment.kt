package com.itis.android_homework.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.data.AppDatabase
import com.itis.android_homework.data.entity.Task
import com.itis.android_homework.databinding.TaskFragmentBinding
import com.itis.android_homework.fragments.date_picker.DatePickerFragment
import com.itis.android_homework.helpers.DateToStringConverter
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

private const val REQUEST_CODE = 1

class TaskFragment : Fragment(R.layout.task_fragment) {

    private var binding: TaskFragmentBinding? = null
    private lateinit var database: AppDatabase
    private lateinit var client: FusedLocationProviderClient
    private var calendar: Calendar? = null
    private var currentTaskId: Int? = null

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        client = LocationServices.getFusedLocationProviderClient(activity)
    }

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
        setLocation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.icon_save -> {
                saveChanges()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setLocation() {
        if (checkPermissions() == true) {
            getCurrentLocation()
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_CODE
            )
        }
    }

    private fun checkPermissions(): Boolean? {
        activity?.apply {
            return (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    == PackageManager.PERMISSION_GRANTED)
        }
        return null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) or
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        ) {
            client.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location != null) {
                    binding?.etLongitude?.setText(location.longitude.toString())
                    binding?.etLatitude?.setText(location.latitude.toString())
                }
            }
        } else {
            startActivity(
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    private fun checkIfTaskExists() {
        arguments?.getInt("ARG_TASK_ID")?.let {
            currentTaskId = it
            setTaskEditingView(it)
        }
    }

    private fun setTaskEditingView(id: Int) {
        scope.launch {
            val task = database.taskDao().getTaskById(id)
            binding?.apply {
                etTitle.setText(task?.title)
                multEtDesc.setText(task?.description)
                task?.date?.let {
                    calendar = Calendar.getInstance()
                    calendar?.time = it
                    tvDate.text = DateToStringConverter.convertDateToString(it)
                    tvDate.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun saveChanges() {
        currentTaskId?.let {
            updateTaskData(it)
        }
        if (currentTaskId == null && isDataCorrect()) {
            addTask()
            showMessage("Задача успешно сохранена.")
            returnToMainFragment()
        }
    }

    private fun updateTaskData(id: Int) {
        if (isDataCorrect()) {
            refreshData(id)
            showMessage("Задача успешно обновлена.")
            returnToMainFragment()
        }
    }

    private fun refreshData(id: Int) {
        scope.launch {
            val task = database.taskDao().getTaskById(id)
            binding?.apply {
                task?.let {
                    it.title = etTitle.text.toString()
                    it.description = multEtDesc.text.toString()
                    calendar?.apply {
                        it.date = time
                    }
                    database.taskDao().updateTask(task)
                }
            }
        }
    }

    private fun addTask() {
        binding?.apply {
            val longitude = if (etLongitude.text.toString().isNotEmpty())
                etLongitude.text.toString().toDouble()
            else null

            val latitude = if (etLatitude.text.toString().isNotEmpty())
                etLatitude.text.toString().toDouble()
            else null

            val newTask = Task(
                0,
                etTitle.text.toString(),
                multEtDesc.text.toString(),
                calendar?.time,
                longitude,
                latitude
            )
            saveNewTask(newTask)
        }
    }

    private fun saveNewTask(newTask: Task) {
        scope.launch {
            database.taskDao().save(newTask)
        }
    }

    private fun isDataCorrect(): Boolean {
        binding?.apply {
            if (etTitle.text.toString().isEmpty()) {
                showMessage("Заполните название.")
                return false
            }
            if (multEtDesc.text.toString().isEmpty()) {
                showMessage("Заполните описание.")
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

    override fun onResume() {
        super.onResume()
        if (checkPermissions() == true)
            getCurrentLocation()
    }

    override fun onDestroyView() {
        scope.cancel()
        super.onDestroyView()
        binding = null
    }
}
