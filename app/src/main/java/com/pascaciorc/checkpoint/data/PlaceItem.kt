package com.pascaciorc.checkpoint.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceItem(
    val coordinate: Coordinate,
    val name: String,
    val address: String,
    var distance: String = ""
) : Parcelable

@Parcelize
data class Coordinate(val lat: Double, val long: Double) : Parcelable

fun Place.toPlaceItem(): PlaceItem {
    return PlaceItem(
        Coordinate(this.geometry.location.lat, this.geometry.location.lng),
        this.name,
        this.vicinity
    )
}
