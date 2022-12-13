package com.example.weathertest.ui.features.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertest.domain.fold
import com.example.weathertest.domain.mapper.CurrentWeatherToCurrentWeatherUIModelMapper
import com.example.weathertest.domain.mapper.ForecastToForecastListMapper
import com.example.weathertest.domain.usecase.GetCityCoordinatesUseCase
import com.example.weathertest.domain.usecase.GetCurrentWeatherUseCase
import com.example.weathertest.domain.usecase.GetForecastUseCase
import com.example.weathertest.ui.features.main.model.CurrentWeatherUIModel
import com.example.weathertest.ui.features.main.model.ForecastList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCityCoordinatesUseCase: GetCityCoordinatesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val currentWeatherToCurrentWeatherUIModelMapper: CurrentWeatherToCurrentWeatherUIModelMapper,
    private val forecastToForecastListMapper: ForecastToForecastListMapper
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _currentWeather = MutableLiveData<CurrentWeatherUIModel>()
    val currentWeather: LiveData<CurrentWeatherUIModel> = _currentWeather

    private val _forecast = MutableLiveData<ForecastList>()
    val forecast: LiveData<ForecastList> = _forecast

    fun loadCurrentWeatherAndForecastFromCity(cityName: String?, numDaysForecast: Int?) {
        viewModelScope.launch {
            _loading.postValue(true)
            getCityCoordinatesUseCase.fetch(cityName).fold(
                leftOp = { exception ->
                    _loading.postValue(false)
                    _error.postValue(exception.message)
                },
                rightOp = { cityCoordinates ->
                    loadCurrentWeatherAndForecast(cityCoordinates.latitude, cityCoordinates.longitude, numDaysForecast)
                }
            )
        }
    }

    private fun loadCurrentWeatherAndForecast(latitude: Double?, longitude: Double?, numDays: Int?) {
        viewModelScope.launch {
            async {
                getCurrentWeatherUseCase.fetch(latitude, longitude).fold(
                    leftOp = { exception ->
                        _loading.postValue(false)
                        _error.postValue(exception.message)
                    },
                    rightOp = { currentWeather ->
                        _loading.postValue(false)
                        _currentWeather.postValue(currentWeatherToCurrentWeatherUIModelMapper.mapFromOtherModel(currentWeather))
                    }
                )
            }

            async {
                getForecastUseCase.fetch(latitude, longitude, numDays).fold(
                    leftOp = { exception ->
                        _loading.postValue(false)
                        _error.postValue(exception.message)
                    },
                    rightOp = { forecast ->
                        _loading.postValue(false)
                        _forecast.postValue(forecastToForecastListMapper.mapFromOtherModel(forecast))
                    }
                )
            }
        }
    }

}