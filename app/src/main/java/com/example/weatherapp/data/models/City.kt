package com.example.weatherapp.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class City(
    val coord: Coord?,
    val country: String?,
    val name: String?,
    val id: Int?,
    val population: Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val timezone: Int?
)