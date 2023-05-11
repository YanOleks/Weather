package com.oleks.weather.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeather(
    @Json(name = "is_day")
    val isDay: Int,
    @Json(name = "temperature")
    val temperature: Double,
    @Json(name = "time")
    val time: String,
    @Json(name = "weathercode")
    val weatherCode: Int,
    @Json(name = "winddirection")
    val windDirection: Double,
    @Json(name = "windspeed")
    val windSpeed: Double
)