package com.itis.android_homework.entities

import com.itis.android_homework.interfaces.IWork

import java.util.*

class Worker(
    dateOfBirth: Calendar,
    gender: Boolean,
    name: String,
    var companyName: String,
    var position: String,
) : Human(dateOfBirth, gender, name), IWork {

    init {
        println("New worker $name added")
    }

    override fun speak() = println("Talking to colleagues...")

    override fun work() = println("Do some job...")
}
