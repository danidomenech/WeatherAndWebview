package com.example.rindustest.domain.mapper

import com.example.rindustest.common.mapper.AbstractMapper
import com.example.rindustest.domain.model.WeatherDay
import com.example.rindustest.ui.features.main.model.ForecastWeatherDayUIModel
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