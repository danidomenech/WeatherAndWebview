package com.example.rindustest.domain.mapper

import com.example.rindustest.common.mapper.AbstractMapper
import com.example.rindustest.domain.model.WeatherDay
import com.example.rindustest.ui.features.main.model.WeatherDayUIModel
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