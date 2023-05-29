package com.oleks.weather.data.geonames.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geoname(
    @Json(name = "adminCode1")
    val adminCode1: String,
//    @Json(name = "adminCodes1")
//    val adminCodes1: AdminCodes1 = null,
    @Json(name = "adminName1")
    val adminName1: String,
    @Json(name = "countryCode")
    val countryCode: String,
    @Json(name = "countryId")
    val countryId: String,
    @Json(name = "countryName")
    val countryName: String,
    @Json(name = "fcl")
    val fcl: String,
    @Json(name = "fclName")
    val fclName: String,
    @Json(name = "fcode")
    val fcode: String,
    @Json(name = "fcodeName")
    val fcodeName: String,
    @Json(name = "geonameId")
    val geonameId: Int,
    @Json(name = "lat")
    val lat: String,
    @Json(name = "lng")
    val lng: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "population")
    val population: Int,
    @Json(name = "toponymName")
    val toponymName: String
)