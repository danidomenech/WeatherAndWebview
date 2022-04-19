package com.example.rindustest.data.network

import com.example.rindustest.data.utils.serviceHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherClient @Inject constructor(private val weatherApi: WeatherApi) {

    suspend fun getCurrentWeather(latitude: Double?, longitude: Double?) =
        serviceHandler {
            weatherApi.getCurrentWeather(latitude, longitude)
        }

    suspend fun getForecast(latitude: Double?, longitude: Double?, numDays: Int?) =
        serviceHandler {
            weatherApi.getForecast(latitude, longitude, numDays)
        }
}