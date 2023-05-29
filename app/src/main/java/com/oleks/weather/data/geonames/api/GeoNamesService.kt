package com.oleks.weather.data.geonames.api

import com.oleks.weather.data.geonames.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoNamesService {
    @GET(GeoNamesConstants.END_POINT)
    suspend fun searchPlaces(
        @Query("name") name: String,
        @Query("lang") lang: String
    ): Response<SearchResponse>
}