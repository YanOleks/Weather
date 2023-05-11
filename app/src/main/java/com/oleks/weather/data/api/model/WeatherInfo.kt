package com.oleks.weather.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherInfo(
    @Json(name = "current_weather")
    val currentWeather: CurrentWeather,
    @Json(name = "daily")
    val daily: Daily,
    @Json(name = "daily_units")
    val dailyUnits: DailyUnits,
    @Json(name = "elevation")
    val elevation: Double,
    @Json(name = "generationtime_ms")
    val generationtimeMs: Double,
    @Json(name = "hourly")
    val hourly: Hourly,
    @Json(name = "hourly_units")
    val hourlyUnits: HourlyUnits,
    @Json(name = "latitude")
    val latitude: Double,
    @Json(name = "longitude")
    val longitude: Double,
    @Json(name = "timezone")
    val timezone: String,
    @Json(name = "timezone_abbreviation")
    val timezoneAbbreviation: String,
    @Json(name = "utc_offset_seconds")
    val utcOffsetSeconds: Int
) {
    @JsonClass(generateAdapter = true)
    data class CurrentWeather(
        @Json(name = "is_day")
        val isDay: Int,
        @Json(name = "temperature")
        val temperature: Double,
        @Json(name = "time")
        val time: String,
        @Json(name = "weathercode")
        val weathercode: Int,
        @Json(name = "winddirection")
        val winddirection: Double,
        @Json(name = "windspeed")
        val windspeed: Double
    )

    @JsonClass(generateAdapter = true)
    data class Daily(
        @Json(name = "precipitation_probability_max")
        val precipitationProbabilityMax: List<Int>,
        @Json(name = "temperature_2m_max")
        val temperature2mMax: List<Double>,
        @Json(name = "temperature_2m_min")
        val temperature2mMin: List<Double>,
        @Json(name = "time")
        val time: List<String>,
        @Json(name = "weathercode")
        val weathercode: List<Int>
    )

    @JsonClass(generateAdapter = true)
    data class DailyUnits(
        @Json(name = "precipitation_probability_max")
        val precipitationProbabilityMax: String,
        @Json(name = "temperature_2m_max")
        val temperature2mMax: String,
        @Json(name = "temperature_2m_min")
        val temperature2mMin: String,
        @Json(name = "time")
        val time: String,
        @Json(name = "weathercode")
        val weathercode: String
    )

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
        val weathercode: List<Int>
    )

    @JsonClass(generateAdapter = true)
    data class HourlyUnits(
        @Json(name = "apparent_temperature")
        val apparentTemperature: String,
        @Json(name = "is_day")
        val isDay: String,
        @Json(name = "precipitation")
        val precipitation: String,
        @Json(name = "precipitation_probability")
        val precipitationProbability: String,
        @Json(name = "temperature_2m")
        val temperature2m: String,
        @Json(name = "time")
        val time: String,
        @Json(name = "weathercode")
        val weathercode: String
    )
}