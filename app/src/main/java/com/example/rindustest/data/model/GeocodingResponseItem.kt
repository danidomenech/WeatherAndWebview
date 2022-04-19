package com.example.rindustest.data.model

data class GeocodingResponseItem(
    val name: String,
    val local_names: LocalNames,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String
)