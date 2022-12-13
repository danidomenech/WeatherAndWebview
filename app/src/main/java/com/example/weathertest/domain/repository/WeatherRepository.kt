package com.example.weathertest.domain.repository

import com.example.weathertest.domain.Either
import com.example.weathertest.domain.model.CurrentWeather
import com.example.weathertest.domain.model.Forecast

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double?, longitude: Double?): Either<Exception, CurrentWeather>
    suspend fun getForecast(latitude: Double?, longitude: Double?, numDays: Int?): Either<Exception, Forecast>
}