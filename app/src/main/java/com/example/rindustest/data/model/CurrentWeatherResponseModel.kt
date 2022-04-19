package com.example.rindustest.data.model

data class CurrentWeatherResponseModel(
    val coord: Coord?,
    val weather: List<WeatherResponseModel>?,
    val base: String?,
    val main: Main?,
    val visibility: Int?,
    val wind: Wind?,
    val clouds: Clouds?,
    val dt: Long?,
    val sys: Sys?,
    val timezone: Int?,
    val id: Int?,
    val name: String?,
    val cod: Int?
)