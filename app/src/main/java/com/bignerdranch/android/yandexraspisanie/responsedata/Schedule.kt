package com.bignerdranch.android.yandexraspisanie.responsedata

data class Schedule(
    val arrival: String,
    val days: String,
    val departure: String,
    val except_days: String,
    val is_fuzzy: Boolean,
    val platform: String,
    val stops: String,
    val terminal: Any,
    val thread: Thread
)