package com.example.weathertest.domain.model

import java.util.*

data class WeatherDay(
    val temperature: Double?,
    val feelsLikeTemperature: Double?,
    val minTemperature: Double?,
    val maxTemperature: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val cloudiness: Int?,
    val windSpeed: Double?,
    val visibilityMeters: Int?,
    val description: String?,
    val date: Date?
)