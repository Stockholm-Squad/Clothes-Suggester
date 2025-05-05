package org.example.data.datasource

import org.example.data.dto.GeoLocationDto
import org.example.data.dto.IpLocationDto

interface LocationDataSource {
    suspend fun getCurrentLocation(): IpLocationDto?
    suspend fun getLocationByCountryAndCity(country: String, city: String): List<GeoLocationDto?>?
}