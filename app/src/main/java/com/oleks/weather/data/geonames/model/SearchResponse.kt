package com.oleks.weather.data.geonames.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "geonames")
    val geonames: List<Geoname>,
    @Json(name = "totalResultsCount")
    val totalResultsCount: Int
)