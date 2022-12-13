package com.example.weathertest.data.repository

import com.example.weathertest.data.mapper.GeocodingResponseToCityCoordinatesMapper
import com.example.weathertest.data.network.GeocodingClient
import com.example.weathertest.domain.Either
import com.example.weathertest.domain.map
import com.example.weathertest.domain.model.CityCoordinates
import com.example.weathertest.domain.repository.GeocodingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeocodingRepositoryImpl (
    private val client: GeocodingClient,
    private val geocodingMapper: GeocodingResponseToCityCoordinatesMapper,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    ) : GeocodingRepository {

    override suspend fun getCityCoordinates(cityName: String?): Either<Exception, CityCoordinates> {
        return withContext(dispatcherIO) {
            client.getCityCoordinates(cityName)
                .map { geocodingResponseModel ->
                    geocodingMapper.mapFromOtherModel(geocodingResponseModel)
                }
        }
    }

}