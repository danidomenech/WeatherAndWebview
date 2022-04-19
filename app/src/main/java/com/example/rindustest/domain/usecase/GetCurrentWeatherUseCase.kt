package com.example.rindustest.domain.usecase

import com.example.rindustest.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend fun fetch(latitude: Double?, longitude: Double?) =
        repository.getCurrentWeather(latitude, longitude)
}