package com.oleks.weather.data.api

import com.oleks.weather.data.api.model.WeatherInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {
    @GET(ApiConstants.END_POINT)
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        //@Query("hourly") hourly: String = "temperature_2m,apparent_temperature,precipitation_probability,precipitation,weathercode,is_day",
        //@Query("daily") daily: String = "weathercode,temperature_2m_max,temperature_2m_min,precipitation_probability_max",
        //@Query("current_weather") currentWeather: Boolean = true,
        //@Query("timezone") timezone: String = "auto"

    ): WeatherInfo
}

data class ErrorResponse(
    val error: Boolean,
    val reason: String,
)

object RetrofitBuilder {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val apiService: WeatherApiService = getRetrofit().create(WeatherApiService::class.java)
}

interface ApiHelper {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherInfo
}

class ApiHelperImpl(private val weatherApiService: WeatherApiService) : ApiHelper {
    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherInfo = weatherApiService.getWeather(latitude, longitude)
}

val apiHelper = ApiHelperImpl(RetrofitBuilder.apiService)