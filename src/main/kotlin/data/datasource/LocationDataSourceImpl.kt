package org.example.data.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.example.data.dto.LocationDto
import org.example.logic.exceptions.LoadingDataException

class LocationDataSourceImpl(
    private val ipApiClient: HttpClient,
    private val geoApiClient: HttpClient,
) : LocationDataSource {

    override suspend fun getCurrentLocation(): LocationDto? {
        try {
            return ipApiClient.get("json").body<LocationDto?>()
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LoadingDataException()
        }
    }

    override suspend fun getLocationByCountryAndCity(country: String, city: String): LocationDto? {
        try {
            return geoApiClient.get("$city&$country").body<LocationDto?>()
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LoadingDataException()
        }
    }
}