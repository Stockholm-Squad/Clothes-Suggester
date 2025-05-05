package org.example.data.datasource

import logic.model.LocationModel

interface LocationDataSource {
    suspend fun getCurrentLocation(): LocationModel?
    suspend fun getLocationByCountry(country: String): LocationModel?
}