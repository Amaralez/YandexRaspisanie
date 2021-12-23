package com.bignerdranch.android.yandexraspisanie.responsedata

data class YandexResponse(
    val date: String,
    val directions: List<Direction>,
    val interval_schedule: List<Any>,
    val pagination: Pagination,
    val schedule: List<Schedule>,
    val schedule_direction: ScheduleDirection,
    val station: Station
)