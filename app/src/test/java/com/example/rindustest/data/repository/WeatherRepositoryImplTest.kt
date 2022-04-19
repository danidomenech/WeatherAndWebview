package com.example.rindustest.data.repository

import com.example.rindustest.data.mapper.CurrentWeatherResponseToCurrentWeatherMapper
import com.example.rindustest.data.mapper.ForecastResponseToForecastMapper
import com.example.rindustest.data.model.CurrentWeatherResponseModel
import com.example.rindustest.data.model.ForecastResponseModel
import com.example.rindustest.data.network.WeatherClient
import com.example.rindustest.domain.*
import com.example.rindustest.domain.model.CurrentWeather
import com.example.rindustest.domain.model.Forecast
import com.example.rindustest.domain.repository.WeatherRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.Exception

@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryImplTest {

    @Mock
    private lateinit var client: WeatherClient

    @Mock
    private lateinit var weatherMapper: CurrentWeatherResponseToCurrentWeatherMapper

    @Mock
    private lateinit var forecastMapper: ForecastResponseToForecastMapper

    private lateinit var repository: WeatherRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        repository = WeatherRepositoryImpl(client, weatherMapper, forecastMapper, testCoroutineDispatcher)
    }

    @Test
    fun getWeahterSuccess() {
        testCoroutineDispatcher.runBlockingTest {
            val mockResponse = mock<CurrentWeatherResponseModel>()
            whenever(client.getCurrentWeather(any(), any())).thenReturn(Either.Right(mockResponse))
            val domainModel = mock<CurrentWeather>()
            whenever(weatherMapper.mapFromOtherModel(mockResponse)).thenReturn(domainModel)

            val result = repository.getCurrentWeather(any(), any())

            assert(result.isRight)
            assertEquals(result.data, domainModel)
        }
    }

    @Test
    fun getWeatherError() {
        testCoroutineDispatcher.runBlockingTest {
            val mockResponse = Exception("message")
            whenever(client.getCurrentWeather(any(), any())).thenReturn(Either.Left(mockResponse))

            val result = repository.getCurrentWeather(any(), any())

            assert(result.isLeft)
            assertEquals(result.error.message, mockResponse.message)
        }
    }

    @Test
    fun getForecastSuccess() {
        testCoroutineDispatcher.runBlockingTest {
            val mockResponse = mock<ForecastResponseModel>()
            whenever(client.getForecast(any(), any(), any())).thenReturn(Either.Right(mockResponse))
            val domainModel = mock<Forecast>()
            whenever(forecastMapper.mapFromOtherModel(mockResponse)).thenReturn(domainModel)

            val result = repository.getForecast(any(), any(), any())

            assert(result.isRight)
            assertEquals(result.data, domainModel)
        }
    }

    @Test
    fun getForecastError() {
        testCoroutineDispatcher.runBlockingTest {
            val mockResponse = Exception("message")
            whenever(client.getForecast(any(), any(), any())).thenReturn(Either.Left(mockResponse))

            val result = repository.getForecast(any(), any(), any())

            assert(result.isLeft)
            assertEquals(result.error.message, mockResponse.message)
        }
    }

}