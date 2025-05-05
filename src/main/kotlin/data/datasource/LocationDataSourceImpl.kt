package org.example.data.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import org.example.data.dto.LocationDto
import org.example.logic.exceptions.LoadingDataException

class LocationDataSourceImpl : LocationDataSource {

    override suspend fun getCurrentLocation(): LocationDto? {
        try {
            val client = HttpClient(CIO)
            val url = "http://ip-api.com/json"
            return client.get(url).body()

        } catch (exception: Exception) {
            throw LoadingDataException()
        }
    }

    override suspend fun getLocationByCountry(country: String, city: String): LocationDto? {
        TODO("Not yet implemented")
    }
}