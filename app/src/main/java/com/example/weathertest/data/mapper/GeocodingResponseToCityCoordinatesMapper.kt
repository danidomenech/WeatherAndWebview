package com.example.weathertest.data.mapper

import com.example.weathertest.common.mapper.AbstractMapper
import com.example.weathertest.data.model.GeocodingResponseModel
import com.example.weathertest.domain.model.CityCoordinates
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocodingResponseToCityCoordinatesMapper @Inject constructor() : AbstractMapper<GeocodingResponseModel, CityCoordinates>() {

    override fun mapFromOtherModel(otherModel: GeocodingResponseModel) =
        with(otherModel) {
            CityCoordinates(
                get(0).name,
                get(0).country,
                get(0).lat,
                get(0).lon
            )
        }
}
