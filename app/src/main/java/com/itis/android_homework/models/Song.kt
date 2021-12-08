package com.itis.android_homework.models

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song(
    val id: Int,
    val title: String,
    val author: String,
    val duration: Int,
    @DrawableRes val cover: Int,
    @RawRes val audio: Int,
)
