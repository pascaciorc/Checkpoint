package com.pascaciorc.checkpoint.api

import com.pascaciorc.checkpoint.BuildConfig
import com.pascaciorc.checkpoint.data.PlacesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesService {

    @GET("nearbysearch/json")
    suspend fun findPlace(
        @Query("keyword") keyword: String,
        @Query("location") location: String,
        @Query("rankby") rankBy: String = "distance",
        @Query("key") key: String = BuildConfig.API_KEY
    ): PlacesResponse

    companion object {
        private const val BASE_URL = "https://maps.googleapis.com/maps/api/place/"

        fun create(): PlacesService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlacesService::class.java)
        }
    }
}