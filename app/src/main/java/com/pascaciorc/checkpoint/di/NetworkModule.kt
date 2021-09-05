package com.pascaciorc.checkpoint.di

import com.pascaciorc.checkpoint.api.PlacesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providePlacesService(): PlacesService {
        return PlacesService.create()
    }
}