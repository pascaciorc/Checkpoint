package com.pascaciorc.checkpoint.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascaciorc.checkpoint.data.CheckpointRepository
import com.pascaciorc.checkpoint.data.Location
import com.pascaciorc.checkpoint.data.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val checkpointRepository: CheckpointRepository,
    private val placesRepository: PlacesRepository
) : ViewModel() {

    fun getNearbyPlaces(keyword: String, location: Location) {
        viewModelScope.launch {
            placesRepository.getNearbyPlaces(keyword, "${location.lat},${location.lng}")
                .onStart {

                }
                .catch {

                }
                .collect {
                    Log.d("DASHBOARD", it.toString())
                }
        }

    }
}