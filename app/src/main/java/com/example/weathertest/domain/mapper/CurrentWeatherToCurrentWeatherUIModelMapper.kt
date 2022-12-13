package com.example.weathertest.domain.mapper

import com.example.weathertest.common.mapper.AbstractMapper
import com.example.weathertest.domain.model.CurrentWeather
import com.example.weathertest.ui.features.main.model.CurrentWeatherUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherToCurrentWeatherUIModelMapper @Inject constructor(private val mapper: WeatherDayToWeatherDayUIModelMapper) : AbstractMapper<CurrentWeather, CurrentWeatherUIModel>() {

    override fun mapFromOtherModel(domainModel: CurrentWeather) =
        with(domainModel) {
            CurrentWeatherUIModel(
                cityName,
                country,
                weather?.let { weather ->
                    mapper.mapFromOtherModel(weather)
                }
            )
        }
}