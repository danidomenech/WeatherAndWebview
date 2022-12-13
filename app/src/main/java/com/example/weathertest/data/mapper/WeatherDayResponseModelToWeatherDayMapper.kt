package com.example.weathertest.data.mapper

import com.example.weathertest.common.mapper.AbstractMapper
import com.example.weathertest.data.model.WeatherDayResponseModel
import com.example.weathertest.domain.model.WeatherDay
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDayResponseModelToWeatherDayMapper @Inject constructor() : AbstractMapper<WeatherDayResponseModel, WeatherDay>() {

    private val DATE_RESPONSE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    override fun mapFromOtherModel(otherModel: WeatherDayResponseModel) =
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
                parseDate(dt_txt)
            )
    }

    fun parseDate(dateText: String?) : Date {
        return SimpleDateFormat(DATE_RESPONSE_FORMAT).parse(dateText)
    }
}