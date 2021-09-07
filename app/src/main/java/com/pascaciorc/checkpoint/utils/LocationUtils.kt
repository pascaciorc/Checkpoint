package com.pascaciorc.checkpoint.utils

import android.content.Context
import android.location.Location
import com.pascaciorc.checkpoint.R
import com.pascaciorc.checkpoint.data.Coordinates
import com.pascaciorc.checkpoint.data.toLocation

fun Context.getDistance(startingLocation: Location, destinationLocation: Location): String {
    val distance = startingLocation.distanceTo(destinationLocation)

    return if (distance < 1000) {
        getString(R.string.meters, distance.toInt())
    } else {
        getString(R.string.kilometers, "%.1f".format(distance / 1000))
    }
}
