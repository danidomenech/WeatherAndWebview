package com.example.rindustest.ui.features.main.model

import java.util.*

data class ForecastWeatherDayUIModel(
    val minTemperature: Double?,
    val maxTemperature: Double?,
    val description: String?,
    val date: Date?
)