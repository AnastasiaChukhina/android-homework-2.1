package com.itis.android_homework.helpers

import java.text.SimpleDateFormat
import java.util.*

object DateToStringConverter {

    fun convertDateToString(date: Date?): String{
        return if(date != null) {
            val dateFormat = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
            dateFormat.format(date)
        }
        else "No date selected"
    }
}
