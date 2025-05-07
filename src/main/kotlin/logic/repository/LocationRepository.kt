package org.example.logic.repository

import logic.model.LocationModel

interface LocationRepository {
    suspend fun getCurrentLocation(): LocationModel?
    suspend fun getLocationByCountryAndCity(country: String, city: String): LocationModel?
}