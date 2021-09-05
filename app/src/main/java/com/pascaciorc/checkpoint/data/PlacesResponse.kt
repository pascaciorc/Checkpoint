package com.pascaciorc.checkpoint.data

import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("results") val results: List<Place>
)

data class Place(
    @field:SerializedName("vicinity") val vicinity: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("geometry") val geometry: Geometry
)

data class Geometry(
    @field:SerializedName("location") val location: Location
)

data class Location(
    @field:SerializedName("lat") val lat: Double,
    @field:SerializedName("lng") val lng: Double
)
