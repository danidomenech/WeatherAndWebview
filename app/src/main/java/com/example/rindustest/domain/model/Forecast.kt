package com.example.rindustest.domain.model

data class Forecast(
    val cityName: String?,
    val country: String?,
    val weatherDays: List<WeatherDay>?
)