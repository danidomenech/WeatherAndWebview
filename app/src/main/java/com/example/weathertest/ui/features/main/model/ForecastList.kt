package com.example.weathertest.ui.features.main.model

data class ForecastList(
    val city: String?,
    val country: String?,
    val weatherDays: List<ForecastWeatherDayUIModel>?
)
