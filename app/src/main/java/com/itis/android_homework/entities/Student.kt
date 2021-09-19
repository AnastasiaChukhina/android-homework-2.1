package com.itis.android_homework.entities

import com.itis.android_homework.interfaces.IStudy

import java.util.*

class Student(
    dateOfBirth: Calendar,
    gender: Boolean,
    name: String,
    var studentId: Int,
    var averageScore: Float,
) : Human(dateOfBirth, gender, name), IStudy {

    init {
        println("New student $name added")
    }

    override fun speak() = println("Talking to classmates...")

    override fun study() = println("Study something...")
}
