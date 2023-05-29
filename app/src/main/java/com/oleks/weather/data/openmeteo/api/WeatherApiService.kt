package com.oleks.weather.data.openmeteo.api

import com.oleks.weather.data.openmeteo.model.WeatherInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApiService {
    @GET(OpenMeteoConstants.END_POINT)
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<WeatherInfo>
}