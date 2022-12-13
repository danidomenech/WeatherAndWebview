package com.example.weathertest.data.repository

import com.example.weathertest.data.mapper.CurrentWeatherResponseToCurrentWeatherMapper
import com.example.weathertest.data.mapper.ForecastResponseToForecastMapper
import com.example.weathertest.data.network.WeatherClient
import com.example.weathertest.domain.Either
import com.example.weathertest.domain.map
import com.example.weathertest.domain.model.CurrentWeather
import com.example.weathertest.domain.model.Forecast
import com.example.weathertest.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val NUM_SECTIONS_PER_DAY = 8
private const val NUM_SECTIONS_FIRST_DAY = 5

class WeatherRepositoryImpl (
    private val client: WeatherClient,
    private val weatherMapper: CurrentWeatherResponseToCurrentWeatherMapper,
    private val forecastMapper: ForecastResponseToForecastMapper,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    ) : WeatherRepository {

    override suspend fun getCurrentWeather(latitude: Double?, longitude: Double?): Either<Exception, CurrentWeather> {
        return withContext(dispatcherIO) {
            client.getCurrentWeather(latitude, longitude)
                .map { currentWeatherResponseModel ->
                    weatherMapper.mapFromOtherModel(currentWeatherResponseModel)
                }
        }
    }

    override suspend fun getForecast(latitude: Double?, longitude: Double?, numDays: Int?): Either<Exception, Forecast> {
        return withContext(dispatcherIO) {
            val cnt = numDays?.let { days ->
                (days-1) * NUM_SECTIONS_PER_DAY + NUM_SECTIONS_FIRST_DAY //First day returns 5 results instead of 8
            } ?: 0
            client.getForecast(latitude, longitude, cnt)
                .map { forecastResponseModel ->
                    forecastMapper.mapFromOtherModel(forecastResponseModel)
                }
        }
    }
}