package com.oleks.weather.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    @Json(name = "apparent_temperature")
    val apparentTemperature: List<Double>,
    @Json(name = "is_day")
    val isDay: List<Int>,
    @Json(name = "precipitation")
    val precipitation: List<Double>,
    @Json(name = "precipitation_probability")
    val precipitationProbability: List<Int>,
    @Json(name = "temperature_2m")
    val temperature2m: List<Double>,
    @Json(name = "time")
    val time: List<String>,
    @Json(name = "weathercode")
    val weatherCode: List<Int>
)