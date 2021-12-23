package com.bignerdranch.android.yandexraspisanie.responsedata

data class Thread(
    val carrier: Carrier,
    val express_type: String,
    val number: String,
    val short_title: String,
    val title: String,
    val transport_subtype: TransportSubtype,
    val transport_type: String,
    val uid: String,
    val vehicle: Any
)