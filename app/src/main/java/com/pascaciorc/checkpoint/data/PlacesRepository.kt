package com.pascaciorc.checkpoint.data

import com.pascaciorc.checkpoint.api.PlacesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesRepository @Inject constructor(private val api: PlacesService) {

    fun getNearbyPlaces(keyword: String, location: String): Flow<PlacesResponse> {
        return flow {
            val response = api.findPlace(keyword, location)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}