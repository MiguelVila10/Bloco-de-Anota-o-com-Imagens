package com.example.postsapp.data

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class Post @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: String = "",
    val image: String,
    val description: String,
    @SuppressLint("NewApi") val date: String = LocalDate.now().toString()
)
