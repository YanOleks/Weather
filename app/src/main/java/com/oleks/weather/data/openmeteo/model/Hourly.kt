package com.oleks.weather.data.openmeteo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    @Json(name = "apparent_temperature")
    val apparentTemperature: List<Double>,

    @Json(name = "is_day")
    val isDay: List<Int>,

    val precipitation: List<Double>,

    @Json(name = "precipitation_probability")
    val precipitationProbability: List<Int>,

    @Json(name = "temperature_2m")
    val temperature2m: List<Double>,

    val time: List<String>,

    @Json(name = "weathercode")
    val weatherCode: List<Int>,

    @Json(name = "relativehumidity_2m")
    val humidity: List<Int>
)