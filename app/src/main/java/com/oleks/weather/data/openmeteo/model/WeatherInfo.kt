package com.oleks.weather.data.openmeteo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherInfo(
    val latitude: Double,
    val longitude: Double,

    @Json(name = "current_weather")
    val currentWeather: CurrentWeather,

    val daily: Daily,

    @Json(name = "daily_units")
    val dailyUnits: DailyUnits,

    val elevation: Double,

    @Json(name = "generationtime_ms")
    val generationtimeMs: Double,

    val hourly: Hourly,

    @Json(name = "hourly_units")
    val hourlyUnits: HourlyUnits,

    val timezone: String,

    @Json(name = "timezone_abbreviation")
    val timezoneAbbreviation: String,

    @Json(name = "utc_offset_seconds")
    val utcOffsetSeconds: Int
)