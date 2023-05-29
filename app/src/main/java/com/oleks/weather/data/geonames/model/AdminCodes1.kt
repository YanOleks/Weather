package com.oleks.weather.data.geonames.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdminCodes1(
    @Json(name = "ISO3166_2")
    val iSO31662: String
)