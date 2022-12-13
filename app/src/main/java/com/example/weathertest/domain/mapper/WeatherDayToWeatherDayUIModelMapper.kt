package com.example.weathertest.domain.mapper

import com.example.weathertest.common.mapper.AbstractMapper
import com.example.weathertest.domain.model.WeatherDay
import com.example.weathertest.ui.features.main.model.WeatherDayUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDayToWeatherDayUIModelMapper @Inject constructor() : AbstractMapper<WeatherDay, WeatherDayUIModel>() {

    override fun mapFromOtherModel(domainModel: WeatherDay) =
        with(domainModel) {
            WeatherDayUIModel(
                temperature,
                feelsLikeTemperature,
                minTemperature,
                maxTemperature,
                pressure,
                humidity,
                cloudiness,
                windSpeed,
                visibilityMeters?.let { it/1000.0 } ?: 0.0,
                description,
                date
            )
        }
}