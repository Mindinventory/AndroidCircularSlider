package com.github.Mindinventory.circularslider.clock_arms.models

data class Time(
    val minutes: Int = 30,
    val seconds: Int = 15,
    val hours: Int = 3,
    val amOrPm: String = "Unknown"
)