package com.pascaciorc.checkpoint.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascaciorc.checkpoint.data.PlacesRepository
import com.pascaciorc.checkpoint.data.PlacesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val repository: PlacesRepository
): ViewModel() {
    private val _state = MutableLiveData<PlacesState>()
    val state: LiveData<PlacesState> get() = _state

    fun getNearbyPlaces(keyword: String, location: Location) {
        viewModelScope.launch {
            repository.getNearbyPlaces(keyword, "${location.latitude},${location.longitude}")
                .onStart {
                    _state.value = PlacesState.Loading
                }
                .onCompletion {
                    _state.value = PlacesState.HideLoader
                }
                .collect { response ->
                    _state.value = PlacesState.Result(response.results)
                }
        }

    }

}