package com.oleks.weather.data.di

import com.oleks.weather.data.api.RetrofitHelper

class WeatherRepo {
    suspend fun getWeather(latitude: Double, longitude: Double) = RetrofitHelper.Service.getWeather(latitude, longitude)
}