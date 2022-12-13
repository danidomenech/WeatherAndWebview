package com.example.weathertest.data.mapper

import com.example.weathertest.common.mapper.AbstractMapper
import com.example.weathertest.data.model.ForecastResponseModel
import com.example.weathertest.domain.model.Forecast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastResponseToForecastMapper @Inject constructor(private val mapper: WeatherDayResponseModelToWeatherDayMapper) : AbstractMapper<ForecastResponseModel, Forecast>() {

    override fun mapFromOtherModel(otherModel: ForecastResponseModel) =
        with(otherModel) {
            Forecast(
                city?.name,
                city?.country,
                otherModel.list?.let { days ->
                    mapper.mapFromOtherModelList(days)
                } ?: listOf()
            )
    }
}