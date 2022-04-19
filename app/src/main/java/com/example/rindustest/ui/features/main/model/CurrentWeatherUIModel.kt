package com.example.rindustest.ui.features.main.model

data class CurrentWeatherUIModel(
    val city: String?,
    val country: String?,
    val weather: WeatherDayUIModel?
)
