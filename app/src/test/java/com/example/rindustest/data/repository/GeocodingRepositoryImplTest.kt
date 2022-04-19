package com.example.rindustest.data.repository

import com.example.rindustest.data.mapper.GeocodingResponseToCityCoordinatesMapper
import com.example.rindustest.data.model.CurrentWeatherResponseModel
import com.example.rindustest.data.model.GeocodingResponseModel
import com.example.rindustest.data.network.GeocodingClient
import com.example.rindustest.domain.*
import com.example.rindustest.domain.model.CityCoordinates
import com.example.rindustest.domain.model.CurrentWeather
import com.example.rindustest.domain.repository.GeocodingRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GeocodingRepositoryImplTest {

    @Mock
    private lateinit var client: GeocodingClient

    @Mock
    private lateinit var geocodingMapper: GeocodingResponseToCityCoordinatesMapper

    private lateinit var repository: GeocodingRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val cityName = "London"

    @Before
    fun setUp() {
        repository = GeocodingRepositoryImpl(client, geocodingMapper, testCoroutineDispatcher)
    }

    @Test
    fun getCoordinatesSuccess() {
        testCoroutineDispatcher.runBlockingTest {
            val mockResponse = mock<GeocodingResponseModel>()
            whenever(client.getCityCoordinates(cityName)).thenReturn(Either.Right(mockResponse))
            val domainModel = mock<CityCoordinates>()
            whenever(geocodingMapper.mapFromOtherModel(mockResponse)).thenReturn(domainModel)

            val result = repository.getCityCoordinates(cityName)

            assert(result.isRight)
            assertEquals(result.data, domainModel)
        }
    }

    @Test
    fun getCoordinatesError() {
        testCoroutineDispatcher.runBlockingTest {
            val mockResponse = Exception("message")
            whenever(client.getCityCoordinates(any())).thenReturn(Either.Left(mockResponse))

            val result = repository.getCityCoordinates(cityName)

            assert(result.isLeft)
            assertEquals(result.error.message, mockResponse.message)
        }
    }

}