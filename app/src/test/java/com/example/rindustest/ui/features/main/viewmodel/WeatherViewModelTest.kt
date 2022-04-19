package com.example.rindustest.ui.features.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rindustest.domain.Either
import com.example.rindustest.domain.mapper.CurrentWeatherToCurrentWeatherUIModelMapper
import com.example.rindustest.domain.mapper.ForecastToForecastListMapper
import com.example.rindustest.domain.model.CityCoordinates
import com.example.rindustest.domain.model.CurrentWeather
import com.example.rindustest.domain.model.Forecast
import com.example.rindustest.domain.usecase.GetCityCoordinatesUseCase
import com.example.rindustest.domain.usecase.GetCurrentWeatherUseCase
import com.example.rindustest.domain.usecase.GetForecastUseCase
import com.example.rindustest.ui.features.main.model.CurrentWeatherUIModel
import com.example.rindustest.ui.features.main.model.ForecastList
import com.example.rindustest.utils.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    @Mock
    private lateinit var getCityCoordinatesUseCase: GetCityCoordinatesUseCase
    @Mock
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    @Mock
    private lateinit var getForecastUseCase: GetForecastUseCase
    @Mock
    private lateinit var currentWeatherToCurrentWeatherUIModelMapper: CurrentWeatherToCurrentWeatherUIModelMapper
    @Mock
    private lateinit var forecastToForecastListMapper: ForecastToForecastListMapper

    @Mock
    private lateinit var weatherObserver: Observer<CurrentWeatherUIModel>
    @Mock
    private lateinit var forecastObserver: Observer<ForecastList>
    @Mock
    private lateinit var errorObserver: Observer<String>
    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    private lateinit var viewModel: WeatherViewModel

    private val cityName = "London"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        viewModel =
            WeatherViewModel(getCityCoordinatesUseCase, getCurrentWeatherUseCase, getForecastUseCase, currentWeatherToCurrentWeatherUIModelMapper, forecastToForecastListMapper)
        viewModel.currentWeather.observeForever(weatherObserver)
        viewModel.forecast.observeForever(forecastObserver)
        viewModel.error.observeForever(errorObserver)
        viewModel.loading.observeForever(loadingObserver)
    }

    @Test
    fun loadCityWeatherAndForecastSuccess() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val coordinatesResult = Either.Right<CityCoordinates>(mock())
            whenever(getCityCoordinatesUseCase.fetch(cityName)).thenReturn(coordinatesResult)

            val weatherResult = Either.Right<CurrentWeather>(mock())
            whenever(getCurrentWeatherUseCase.fetch(coordinatesResult.data.latitude, coordinatesResult.data.longitude)).thenReturn(weatherResult)

            val forecastResult = Either.Right<Forecast>(mock())
            whenever(getForecastUseCase.fetch(coordinatesResult.data.latitude, coordinatesResult.data.longitude, 1)).thenReturn(forecastResult)

            val weatherMapperResult = mock<CurrentWeatherUIModel>()
            whenever(currentWeatherToCurrentWeatherUIModelMapper.mapFromOtherModel(weatherResult.data)).thenReturn(weatherMapperResult)
            val forecastMapperResult = mock<ForecastList>()
            whenever(forecastToForecastListMapper.mapFromOtherModel(forecastResult.data)).thenReturn(forecastMapperResult)

            viewModel.loadCurrentWeatherAndForecastFromCity(cityName, 1)

            verify(loadingObserver).onChanged(true)
            verify(weatherObserver).onChanged(weatherMapperResult)
            verify(forecastObserver).onChanged(forecastMapperResult)
            verify(errorObserver, never()).onChanged(any())
            verify(loadingObserver, times(2)).onChanged(false)
        }

    @Test
    fun loadCityWeatherAndForecastCoordinatesError() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val coordinatesResult = Either.Left(Exception("coordinates error message"))
            whenever(getCityCoordinatesUseCase.fetch(cityName)).thenReturn(coordinatesResult)

            viewModel.loadCurrentWeatherAndForecastFromCity(cityName, 1)

            verify(loadingObserver).onChanged(true)
            verify(weatherObserver, never()).onChanged(any())
            verify(forecastObserver, never()).onChanged(any())
            verify(errorObserver, times(1)).onChanged(coordinatesResult.error.message)
            verify(loadingObserver, times(1)).onChanged(false)
        }

    @Test
    fun loadCityWeatherAndForecastWeatherError() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val coordinatesResult = Either.Right<CityCoordinates>(mock())
            whenever(getCityCoordinatesUseCase.fetch(cityName)).thenReturn(coordinatesResult)

            val weatherResult = Either.Left(Exception("weather error message"))
            whenever(getCurrentWeatherUseCase.fetch(coordinatesResult.data.latitude, coordinatesResult.data.longitude)).thenReturn(weatherResult)

            val forecastResult = Either.Right<Forecast>(mock())
            whenever(getForecastUseCase.fetch(coordinatesResult.data.latitude, coordinatesResult.data.longitude, 1)).thenReturn(forecastResult)

            val forecastMapperResult = mock<ForecastList>()
            whenever(forecastToForecastListMapper.mapFromOtherModel(forecastResult.data)).thenReturn(forecastMapperResult)

            viewModel.loadCurrentWeatherAndForecastFromCity(cityName, 1)

            verify(loadingObserver).onChanged(true)
            verify(weatherObserver, never()).onChanged(any())
            verify(forecastObserver).onChanged(forecastMapperResult)
            verify(errorObserver, times(1)).onChanged(weatherResult.error.message)
            verify(loadingObserver, times(2)).onChanged(false)
        }

    @Test
    fun loadCityWeatherAndForecastForecastError() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val coordinatesResult = Either.Right<CityCoordinates>(mock())
            whenever(getCityCoordinatesUseCase.fetch(cityName)).thenReturn(coordinatesResult)

            val weatherResult = Either.Right<CurrentWeather>(mock())
            whenever(getCurrentWeatherUseCase.fetch(coordinatesResult.data.latitude, coordinatesResult.data.longitude)).thenReturn(weatherResult)

            val forecastResult = Either.Left(Exception("forecast error message"))
            whenever(getForecastUseCase.fetch(coordinatesResult.data.latitude, coordinatesResult.data.longitude, 1)).thenReturn(forecastResult)

            val weatherMapperResult = mock<CurrentWeatherUIModel>()
            whenever(currentWeatherToCurrentWeatherUIModelMapper.mapFromOtherModel(weatherResult.data)).thenReturn(weatherMapperResult)

            viewModel.loadCurrentWeatherAndForecastFromCity(cityName, 1)

            verify(loadingObserver).onChanged(true)
            verify(weatherObserver).onChanged(weatherMapperResult)
            verify(forecastObserver, never()).onChanged(any())
            verify(errorObserver, times(1)).onChanged(forecastResult.error.message)
            verify(loadingObserver, times(2)).onChanged(false)
        }

    @Test
    fun loadCityWeatherAndForecastWeatherAndError() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val coordinatesResult = Either.Right<CityCoordinates>(mock())
            whenever(getCityCoordinatesUseCase.fetch(cityName)).thenReturn(coordinatesResult)

            val weatherResult = Either.Left(Exception("weather error message"))
            whenever(getCurrentWeatherUseCase.fetch(coordinatesResult.data.latitude, coordinatesResult.data.longitude)).thenReturn(weatherResult)

            val forecastResult = Either.Left(Exception("forecast error message"))
            whenever(getForecastUseCase.fetch(coordinatesResult.data.latitude, coordinatesResult.data.longitude, 1)).thenReturn(forecastResult)

            viewModel.loadCurrentWeatherAndForecastFromCity(cityName, 1)

            verify(loadingObserver).onChanged(true)
            verify(weatherObserver, never()).onChanged(any())
            verify(forecastObserver, never()).onChanged(any())
            verify(errorObserver).onChanged(weatherResult.error.message)
            verify(errorObserver).onChanged(forecastResult.error.message)
            verify(loadingObserver, times(2)).onChanged(false)
        }
}