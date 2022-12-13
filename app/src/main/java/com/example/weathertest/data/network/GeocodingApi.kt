package com.example.weathertest.data.network

import com.example.weathertest.data.model.GeocodingResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val GET_CITY_COORDINATES_PATH = "geo/1.0/direct"

private const val CITY_NAME_PARAM = "q"
private const val LIMIT_PARAM = "limit"

private const val LIMIT_VALUE = 1

interface GeocodingApi {

    @GET(GET_CITY_COORDINATES_PATH)
    suspend fun getCityCoordinates(
        @Query(CITY_NAME_PARAM) cityName: String?,
        @Query(LIMIT_PARAM) limit: Int? = LIMIT_VALUE,
    ): Response<GeocodingResponseModel>

}