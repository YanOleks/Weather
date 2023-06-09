package com.oleks.weather.data.openmeteo.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object OpenMeteoHelper {
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(OpenMeteoConstants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    internal val Service: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}