package com.pascaciorc.checkpoint.data

import android.location.Location
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceItem(
    val coordinates: Coordinates,
    val name: String,
    val address: String,
    var distance: String = ""
) : Parcelable

@Parcelize
data class Coordinates(val lat: Double, val lng: Double) : Parcelable

fun Place.toPlaceItem(): PlaceItem {
    return PlaceItem(
        Coordinates(this.geometry.location.lat, this.geometry.location.lng),
        this.name,
        this.vicinity
    )
}

fun Location.toCoordinates(): Coordinates {
    return Coordinates(this.latitude, this.longitude)
}

fun Coordinates.toLocation(): Location {
    return Location("").also {
        it.latitude = this.lat
        it.longitude = this.lng
    }
}
