package com.oleks.weather.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyUnits(
    @Json(name = "precipitation_probability_max")
    val precipitationProbabilityMax: String,

    @Json(name = "temperature_2m_max")
    val temperature2mMax: String,

    @Json(name = "temperature_2m_min")
    val temperature2mMin: String,

    val time: String,

    @Json(name = "weathercode")
    val weatherCode: String
)