package com.example.weathertest.di

import com.example.weathertest.data.network.GeocodingApi
import com.example.weathertest.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun providesGeocodingApi(retrofit: Retrofit): GeocodingApi = retrofit.create(GeocodingApi::class.java)

    @Provides
    @Singleton
    fun providesWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

}