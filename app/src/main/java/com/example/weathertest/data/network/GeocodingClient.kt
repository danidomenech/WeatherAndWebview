package com.example.weathertest.data.network

import com.example.weathertest.data.utils.serviceHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocodingClient @Inject constructor(private val geocodingApi: GeocodingApi) {

    suspend fun getCityCoordinates(cityName: String?) =
        serviceHandler {
            geocodingApi.getCityCoordinates(cityName)
        }
}