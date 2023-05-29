package com.oleks.weather.data.geonames

import com.oleks.weather.data.geonames.api.GeoNamesHelper
import com.oleks.weather.data.geonames.model.Geoname
import java.io.IOException

class PlacesRepo {
    suspend fun searchPlaces(name: String, lang: String = "en"): List<Geoname> {
        val response = GeoNamesHelper.geoNamesApi.searchPlaces(name, lang)
        if (response.isSuccessful) {
            val searchResponse = response.body()
            return searchResponse?.geonames ?: emptyList()
        } else {
            // Handle error case
            throw IOException("Search request failed with code ${response.code()}")
        }
    }
}