package com.example.rindustest.data.model

data class WeatherDayResponseModel(
    val dt: Int?,
    val main: MainForecast?,
    val weather: List<WeatherResponseModel>?,
    val clouds: Clouds?,
    val wind: Wind?,
    val visibility: Int?,
    val pop: Double?,
    val sys: SysForecast?,
    val dt_txt: String?
)