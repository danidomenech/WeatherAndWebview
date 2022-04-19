package com.example.rindustest.data.network

import com.example.rindustest.data.utils.serviceHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocodingClient @Inject constructor(private val geocodingApi: GeocodingApi) {

    suspend fun getCityCoordinates(cityName: String?) =
        serviceHandler {
            geocodingApi.getCityCoordinates(cityName)
        }
}