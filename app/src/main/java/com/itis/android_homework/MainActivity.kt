package com.itis.android_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itis.android_homework.entities.Student
import com.itis.android_homework.entities.Worker
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val s1 = Student(
            GregorianCalendar(2002, Calendar.JANUARY, 21), false,
            "Anastasia", 1, 92.73F
        )
        val w1 = Worker(
            GregorianCalendar(1851, Calendar.DECEMBER, 30), true,
            "Asa Griggs", "Coca-Cola", "founder"
        )
    }
}
