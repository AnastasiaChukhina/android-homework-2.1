package com.itis.android_homework.entities

import java.util.*

abstract class Human(
    val dateOfBirth: Calendar,
    var gender: Boolean,
    var name: String,
) {

    open fun eat() = println("Eating something...")

    fun sleep() = println("Sleeping...")

    open fun speak() = println("Talking to someone...")
}
