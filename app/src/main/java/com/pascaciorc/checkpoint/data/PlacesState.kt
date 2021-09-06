package com.pascaciorc.checkpoint.data

sealed class PlacesState {
    object Loading : PlacesState()
    object HideLoader : PlacesState()
    class Result(val places: List<Place>) : PlacesState()
}
