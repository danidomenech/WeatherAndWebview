package com.example.rindustest.data.mapper

import com.example.rindustest.common.mapper.AbstractMapper
import com.example.rindustest.data.model.CurrentWeatherResponseModel
import com.example.rindustest.domain.model.CurrentWeather
import com.example.rindustest.domain.model.WeatherDay
import java.text.SimpleDateFormat
import java.util.*
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
