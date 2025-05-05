package org.example.data.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import locationBaseUrl
import org.example.data.dto.LocationDto
import org.example.logic.exceptions.LoadingDataException

class LocationDataSourceImpl(
    private val client: HttpClient
) : LocationDataSource {

    override suspend fun getCurrentLocation(): LocationDto? {
        try {
            return client.get(locationBaseUrl).body<LocationDto?>()

        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LoadingDataException()
        }
    }

    override suspend fun getLocationByCountry(country: String, city: String): LocationDto? {
        TODO("Not yet implemented")
    }
}