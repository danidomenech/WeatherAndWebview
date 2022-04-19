package com.example.rindustest.domain.usecase

import com.example.rindustest.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend fun fetch(latitude: Double?, longitude: Double?, numDays: Int?) =
        repository.getForecast(latitude, longitude, numDays)
}