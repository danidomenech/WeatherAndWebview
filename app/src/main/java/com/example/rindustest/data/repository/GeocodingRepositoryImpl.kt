package com.example.rindustest.data.repository

import com.example.rindustest.data.mapper.GeocodingResponseToCityCoordinatesMapper
import com.example.rindustest.data.network.GeocodingClient
import com.example.rindustest.domain.Either
import com.example.rindustest.domain.map
import com.example.rindustest.domain.model.CityCoordinates
import com.example.rindustest.domain.repository.GeocodingRepository
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