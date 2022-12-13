package com.example.weathertest.domain.model

data class Forecast(
    val cityName: String?,
    val country: String?,
    val weatherDays: List<WeatherDay>?
)