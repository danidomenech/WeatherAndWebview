package com.example.rindustest.data.network

import com.example.rindustest.data.model.CurrentWeatherResponseModel
import com.example.rindustest.data.model.ForecastResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val GET_WEATHER_PATH = "data/2.5/weather"
private const val GET_FORECAST_PATH = "data/2.5/forecast"

private const val LATITUDE_PARAM = "lat"
private const val LONGITUDE_PARAM = "lon"
private const val NUM_DAYS_PARAM = "cnt"
private const val UNITS_PARAM = "units"

private const val METRIC_VALUE = "metric"

interface WeatherApi {

    @GET(GET_WEATHER_PATH)
    suspend fun getCurrentWeather(
        @Query(LATITUDE_PARAM) latitude: Double?,
        @Query(LONGITUDE_PARAM) longitude: Double?,
        @Query(UNITS_PARAM) units: String? = METRIC_VALUE,
    ): Response<CurrentWeatherResponseModel>

    @GET(GET_FORECAST_PATH)
    suspend fun getForecast(
        @Query(LATITUDE_PARAM) latitude: Double?,
        @Query(LONGITUDE_PARAM) longitude: Double?,
        @Query(NUM_DAYS_PARAM) numDays: Int?,
        @Query(UNITS_PARAM) units: String? = METRIC_VALUE,
    ): Response<ForecastResponseModel>

}