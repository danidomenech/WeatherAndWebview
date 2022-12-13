package com.example.weathertest.domain.usecase

import com.example.weathertest.domain.repository.GeocodingRepository
import javax.inject.Inject

class GetCityCoordinatesUseCase @Inject constructor(private val repository: GeocodingRepository) {
    suspend fun fetch(cityName: String?) =
        repository.getCityCoordinates(cityName)
}