package com.example.weathertest.domain.repository

import com.example.weathertest.domain.Either
import com.example.weathertest.domain.model.CityCoordinates

interface GeocodingRepository {
    suspend fun getCityCoordinates(cityName: String?): Either<Exception, CityCoordinates>
}