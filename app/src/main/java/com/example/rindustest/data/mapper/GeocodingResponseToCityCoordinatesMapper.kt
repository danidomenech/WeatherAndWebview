package com.example.rindustest.data.mapper

import com.example.rindustest.common.mapper.AbstractMapper
import com.example.rindustest.data.model.GeocodingResponseModel
import com.example.rindustest.domain.model.CityCoordinates
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
