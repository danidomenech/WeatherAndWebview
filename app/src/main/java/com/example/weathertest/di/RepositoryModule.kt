package com.example.weathertest.di

import com.example.weathertest.data.mapper.CurrentWeatherResponseToCurrentWeatherMapper
import com.example.weathertest.data.mapper.ForecastResponseToForecastMapper
import com.example.weathertest.data.mapper.GeocodingResponseToCityCoordinatesMapper
import com.example.weathertest.data.network.GeocodingClient
import com.example.weathertest.data.network.WeatherClient
import com.example.weathertest.data.repository.GeocodingRepositoryImpl
import com.example.weathertest.data.repository.WeatherRepositoryImpl
import com.example.weathertest.domain.repository.GeocodingRepository
import com.example.weathertest.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGeocodingRepository(
        client: GeocodingClient,
        geocodingMapper: GeocodingResponseToCityCoordinatesMapper
    ): GeocodingRepository = GeocodingRepositoryImpl(client, geocodingMapper)

    @Provides
    @Singleton
    fun provideWeatherRepository(
        client: WeatherClient,
        weatherMapper: CurrentWeatherResponseToCurrentWeatherMapper,
        forecastMapper: ForecastResponseToForecastMapper
    ): WeatherRepository = WeatherRepositoryImpl(client, weatherMapper, forecastMapper)

}