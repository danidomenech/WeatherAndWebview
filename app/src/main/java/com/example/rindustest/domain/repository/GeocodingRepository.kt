package com.example.rindustest.domain.repository

import com.example.rindustest.domain.Either
import com.example.rindustest.domain.model.CityCoordinates

interface GeocodingRepository {
    suspend fun getCityCoordinates(cityName: String?): Either<Exception, CityCoordinates>
}