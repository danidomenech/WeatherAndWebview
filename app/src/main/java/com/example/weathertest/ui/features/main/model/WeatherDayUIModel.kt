package com.example.weathertest.ui.features.main.model

import java.util.*

data class WeatherDayUIModel(
    val temperature: Double?,
    val feelsLikeTemperature: Double?,
    val minTemperature: Double?,
    val maxTemperature: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val cloudiness: Int?,
    val windSpeed: Double?,
    val visibilityKilometers: Double?,
    val description: String?,
    val date: Date?
)