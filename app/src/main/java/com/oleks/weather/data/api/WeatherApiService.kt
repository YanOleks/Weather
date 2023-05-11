package com.oleks.weather.data.api

import com.oleks.weather.data.model.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET(ApiConstants.END_POINT)
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherInfo
}