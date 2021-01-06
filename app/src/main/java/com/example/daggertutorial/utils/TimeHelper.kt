package com.example.daggertutorial.utils

import android.os.SystemClock

fun getTime() = SystemClock.uptimeMillis()

fun elapsedTime(time: Long) = SystemClock.uptimeMillis() - time