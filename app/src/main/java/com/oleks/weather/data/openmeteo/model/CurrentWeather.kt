package com.oleks.weather.data.openmeteo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeather(
    @Json(name = "is_day")
    val isDay: Int,

    val temperature: Double,

    val time: String,

    @Json(name = "weathercode")
    val weatherCode: Int,

    @Json(name = "winddirection")
    val windDirection: Double,

    @Json(name = "windspeed")
    val windSpeed: Double
)