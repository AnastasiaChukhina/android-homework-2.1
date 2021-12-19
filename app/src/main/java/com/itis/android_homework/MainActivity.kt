package com.itis.android_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = findController(R.id.container)
    }

    override fun onBackPressed() {
        when(supportFragmentManager.backStackEntryCount){
            0 -> super.onBackPressed()
            else -> supportFragmentManager.popBackStack()
        }
    }
}
