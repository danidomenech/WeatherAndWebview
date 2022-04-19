package com.example.rindustest.domain.usecase

import com.example.rindustest.domain.repository.GeocodingRepository
import javax.inject.Inject

class GetCityCoordinatesUseCase @Inject constructor(private val repository: GeocodingRepository) {
    suspend fun fetch(cityName: String?) =
        repository.getCityCoordinates(cityName)
}