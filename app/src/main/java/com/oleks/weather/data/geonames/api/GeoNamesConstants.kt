package com.oleks.weather.data.geonames.api

internal object GeoNamesConstants {
    const val BASE_URL = "http://api.geonames.org/"
    private const val USERNAME = "superoleksmyweath"
    const val END_POINT = "searchJSON?maxRows=3&username=$USERNAME"
}