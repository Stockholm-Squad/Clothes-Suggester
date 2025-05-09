package org.example.data.datasource.location

import org.example.data.dto.GeoLocationDto
import org.example.data.dto.IpLocationDto

interface LocationDataSource {
    suspend fun getCurrentLocation(): IpLocationDto?
    suspend fun getLocationByCountryAndCity(country: String, city: String): List<GeoLocationDto?>?
}