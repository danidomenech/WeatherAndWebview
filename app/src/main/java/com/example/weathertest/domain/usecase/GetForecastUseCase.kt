package com.example.weathertest.domain.usecase

import com.example.weathertest.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend fun fetch(latitude: Double?, longitude: Double?, numDays: Int?) =
        repository.getForecast(latitude, longitude, numDays)
}