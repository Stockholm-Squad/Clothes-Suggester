package org.example.data.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.example.data.dto.GeoLocationDto
import org.example.data.dto.GeoLocationResponse
import org.example.data.dto.IpLocationDto
import org.example.logic.exceptions.LoadingDataException

class LocationDataSourceImpl(
    private val ipApiClient: HttpClient,
    private val geoApiClient: HttpClient,
) : LocationDataSource {

    override suspend fun getCurrentLocation(): IpLocationDto? {
        try {
            return ipApiClient.get("json").body<IpLocationDto?>()
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LoadingDataException()
        }
    }

    override suspend fun getLocationByCountryAndCity(country: String, city: String): List<GeoLocationDto?>? {
        try {
            return geoApiClient.get("search") {
                parameter("country", country)
                parameter("name", city)
            }.body<GeoLocationResponse>().results
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LoadingDataException()
        }
    }
}