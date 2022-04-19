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
