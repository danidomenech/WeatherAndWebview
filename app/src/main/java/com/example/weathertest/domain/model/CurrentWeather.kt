package com.example.weathertest.domain.model

data class CurrentWeather(
    val cityName: String?,
    val country: String?,
    val weather: WeatherDay?
)
