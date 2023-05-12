package com.oleks.weather.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyUnits(
    @Json(name = "apparent_temperature")
    val apparentTemperature: String,

    @Json(name = "is_day")
    val isDay: String,

    val precipitation: String,

    @Json(name = "precipitation_probability")
    val precipitationProbability: String,

    @Json(name = "temperature_2m")
    val temperature2m: String,

    val time: String,

    @Json(name = "weathercode")
    val weatherCode: String
)