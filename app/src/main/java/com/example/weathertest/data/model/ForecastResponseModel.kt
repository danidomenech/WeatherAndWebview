package com.example.weathertest.data.model

data class ForecastResponseModel(
    val cod: String?,
    val message: Int?,
    val cnt: Int?,
    val list: List<WeatherDayResponseModel>?,
    val city: City?
)