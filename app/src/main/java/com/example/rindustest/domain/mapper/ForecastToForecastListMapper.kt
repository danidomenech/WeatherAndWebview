package com.example.rindustest.domain.mapper

import com.example.rindustest.common.mapper.AbstractMapper
import com.example.rindustest.domain.model.Forecast
import com.example.rindustest.ui.features.main.model.ForecastList
import com.example.rindustest.ui.features.main.model.ForecastWeatherDayUIModel
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastToForecastListMapper @Inject constructor(private val mapper: WeatherDayToForecastWeatherDayUIModelMapper) : AbstractMapper<Forecast, ForecastList>() {

    override fun mapFromOtherModel(domainModel: Forecast) : ForecastList {
        return with(domainModel) {
            ForecastList(
                cityName,
                country,
                getFilteredDayList(getDayListMapped(list(domainModel)))
            )
        }
    }

    private fun list(domainModel: Forecast): List<ForecastWeatherDayUIModel> =
        domainModel.weatherDays?.let { days ->
            mapper.mapFromOtherModelList(days)
        } ?: listOf()

    private fun getDayListMapped(list: List<ForecastWeatherDayUIModel>) : Map<Int, List<ForecastWeatherDayUIModel>> {
        return list.groupBy {
            val c = Calendar.getInstance()
            c.time = it.date
            c.get(Calendar.DAY_OF_MONTH)
        }
    }

    private fun getFilteredDayList(mappedList: Map<Int, List<ForecastWeatherDayUIModel>>) : List<ForecastWeatherDayUIModel> {
        val finalList = mutableListOf<ForecastWeatherDayUIModel>()
        mappedList.mapKeys {
            val minTemperature = minValue(it.value)?.minTemperature
            val maxTemperature = maxValue(it.value)?.maxTemperature
            val multipleDescription = it.value.distinctBy {
                it.description
            }.joinToString("\n") {
                it.description.toString()
            }

            val element = ForecastWeatherDayUIModel(minTemperature, maxTemperature, multipleDescription, it.value[0].date)
            finalList.add(element)

        }
        return finalList
    }

    private fun maxValue(list: List<ForecastWeatherDayUIModel>): ForecastWeatherDayUIModel? {
        return list.maxByOrNull {
            it.maxTemperature ?: 0.0
        }
    }

    private fun minValue(list: List<ForecastWeatherDayUIModel>): ForecastWeatherDayUIModel? {
        return list.minByOrNull {
            it.minTemperature ?: 0.0
        }
    }
}