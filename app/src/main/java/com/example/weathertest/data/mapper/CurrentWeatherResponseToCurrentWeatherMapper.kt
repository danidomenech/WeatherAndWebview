package com.example.weathertest.data.mapper

import com.example.weathertest.common.mapper.AbstractMapper
import com.example.weathertest.data.model.CurrentWeatherResponseModel
import com.example.weathertest.domain.model.CurrentWeather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherResponseToCurrentWeatherMapper @Inject constructor(private val mapper: CurrentWeatherResponseToWeatherDayMapper) : AbstractMapper<CurrentWeatherResponseModel, CurrentWeather>() {

    override fun mapFromOtherModel(otherModel: CurrentWeatherResponseModel) =
        with(otherModel) {
            CurrentWeather(
                name,
                sys?.country,
                mapper.mapFromOtherModel(otherModel)
            )
        }
}
