package com.github.Mindinventory.circularslider

data class WeeklyUpdateModel(
    val daysList: String,
    val staticProgress: Float
)

fun populateWeeklyUpdateList(): ArrayList<WeeklyUpdateModel> {
    val weeklyUpdateList = arrayListOf<WeeklyUpdateModel>()
    return weeklyUpdateList.apply {
        add(WeeklyUpdateModel(daysList = "Monday", staticProgress = 20f))
        add(WeeklyUpdateModel(daysList = "Tuesday", staticProgress = 60f))
        add(WeeklyUpdateModel(daysList = "Wednesday", staticProgress = 35f))
        add(WeeklyUpdateModel(daysList = "Thursday", staticProgress = 90f))
        add(WeeklyUpdateModel(daysList = "Friday", staticProgress = 43f))
    }
}