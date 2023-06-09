package com.oleks.weather.data.openmeteo.api

internal object OpenMeteoConstants {
    const val BASE_URL = "https://api.open-meteo.com/v1/"
    const val END_POINT = "forecast?hourly=temperature_2m,apparent_temperature,precipitation_probability,precipitation,weathercode,is_day,relativehumidity_2m&daily=weathercode,temperature_2m_max,temperature_2m_min,precipitation_probability_max&current_weather=true&timezone=auto"
}