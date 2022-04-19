package com.example.rindustest.di

import com.example.rindustest.data.mapper.CurrentWeatherResponseToCurrentWeatherMapper
import com.example.rindustest.data.mapper.ForecastResponseToForecastMapper
import com.example.rindustest.data.mapper.GeocodingResponseToCityCoordinatesMapper
import com.example.rindustest.data.network.GeocodingClient
import com.example.rindustest.data.network.WeatherClient
import com.example.rindustest.data.repository.GeocodingRepositoryImpl
import com.example.rindustest.data.repository.WeatherRepositoryImpl
import com.example.rindustest.domain.repository.GeocodingRepository
import com.example.rindustest.domain.repository.WeatherRepository
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