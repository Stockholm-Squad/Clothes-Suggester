package org.example.data.datasource

import org.example.data.dto.LocationDto

interface LocationDataSource {
    suspend fun getCurrentLocation(): LocationDto?
    suspend fun getLocationByCountryAndCity(country: String, city: String): LocationDto?
}