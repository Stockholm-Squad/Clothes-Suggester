package org.example.data.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.data.dto.LocationDto
import org.example.logic.exceptions.LoadingDataException

class LocationDataSourceImpl : LocationDataSource {

    override suspend fun getCurrentLocation(): LocationDto? {
        try {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(Json { ignoreUnknownKeys = true })
                }
            }
            return client.get("http://ip-api.com/json").body<LocationDto>()

        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LoadingDataException()
        }
    }

    override suspend fun getLocationByCountry(country: String, city: String): LocationDto? {
        TODO("Not yet implemented")
    }
}