package com.example.weathertest.data.mapper

import com.example.weathertest.common.mapper.AbstractMapper
import com.example.weathertest.data.model.CurrentWeatherResponseModel
import com.example.weathertest.domain.model.WeatherDay
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherResponseToWeatherDayMapper @Inject constructor() : AbstractMapper<CurrentWeatherResponseModel, WeatherDay>() {

    override fun mapFromOtherModel(otherModel: CurrentWeatherResponseModel) =
        with(otherModel) {
            WeatherDay(
                main?.temp,
                main?.feels_like,
                main?.temp_min,
                main?.temp_max,
                main?.pressure,
                main?.humidity,
                clouds?.all,
                wind?.speed,
                visibility,
                weather?.get(0)?.description,
                Date()
            )
        }
}
