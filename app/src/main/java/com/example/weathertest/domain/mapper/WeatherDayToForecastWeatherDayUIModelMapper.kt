package com.example.weathertest.domain.mapper

import com.example.weathertest.common.mapper.AbstractMapper
import com.example.weathertest.domain.model.WeatherDay
import com.example.weathertest.ui.features.main.model.ForecastWeatherDayUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDayToForecastWeatherDayUIModelMapper @Inject constructor() : AbstractMapper<WeatherDay, ForecastWeatherDayUIModel>() {

    override fun mapFromOtherModel(domainModel: WeatherDay) =
        with(domainModel) {
            ForecastWeatherDayUIModel(
                minTemperature,
                maxTemperature,
                description,
                date
            )
        }
}