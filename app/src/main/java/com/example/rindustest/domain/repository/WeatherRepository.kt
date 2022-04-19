package com.example.rindustest.domain.repository

import com.example.rindustest.domain.Either
import com.example.rindustest.domain.model.CurrentWeather
import com.example.rindustest.domain.model.Forecast

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double?, longitude: Double?): Either<Exception, CurrentWeather>
    suspend fun getForecast(latitude: Double?, longitude: Double?, numDays: Int?): Either<Exception, Forecast>
}