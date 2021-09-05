package com.pascaciorc.checkpoint.data

import android.location.Location
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceRequest(val location: Location, var name: String) : Parcelable
