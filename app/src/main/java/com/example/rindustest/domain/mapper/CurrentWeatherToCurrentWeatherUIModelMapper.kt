package com.example.rindustest.domain.mapper

import com.example.rindustest.common.mapper.AbstractMapper
import com.example.rindustest.domain.model.CurrentWeather
import com.example.rindustest.domain.model.Forecast
import com.example.rindustest.ui.features.main.model.CurrentWeatherUIModel
import com.example.rindustest.ui.features.main.model.ForecastList
import com.example.rindustest.ui.features.main.model.WeatherDayUIModel
import java.util.*
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