package com.oleks.weather.data.di

import com.oleks.weather.data.openmeteo.api.OpenMeteoHelper

class WeatherRepo {
    suspend fun getWeather(latitude: Double, longitude: Double) = OpenMeteoHelper.Service.getWeather(latitude, longitude)
}