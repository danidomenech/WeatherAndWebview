package com.example.weathertest.ui.features.main.model

data class CurrentWeatherUIModel(
    val city: String?,
    val country: String?,
    val weather: WeatherDayUIModel?
)
