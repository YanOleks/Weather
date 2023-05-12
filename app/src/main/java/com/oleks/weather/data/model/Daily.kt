package com.oleks.weather.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Daily(
    @Json(name = "precipitation_probability_max")
    val precipitationProbabilityMax: List<Int>,

    @Json(name = "temperature_2m_max")
    val temperature2mMax: List<Double>,

    @Json(name = "temperature_2m_min")
    val temperature2mMin: List<Double>,

    val time: List<String>,

    @Json(name = "weathercode")
    val weatherCode: List<Int>
)