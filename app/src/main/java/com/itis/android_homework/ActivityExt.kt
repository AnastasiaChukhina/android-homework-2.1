package com.itis.android_homework

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findController(
    id: Int
) : NavController = (supportFragmentManager.findFragmentById(id) as NavHostFragment)
    .navController
