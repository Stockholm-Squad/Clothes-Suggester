package org.example.data.datasource.location

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.example.data.dto.GeoLocationDto
import org.example.data.dto.GeoLocationResponse
import org.example.data.dto.IpLocationDto

class LocationDataSourceImpl(
    private val ipApiClient: HttpClient,
    private val geoApiClient: HttpClient,
) : LocationDataSource {

    override suspend fun getCurrentLocation(): IpLocationDto? {
        return ipApiClient.get("json").body<IpLocationDto?>()
    }

    override suspend fun getLocationByCountryAndCity(country: String, city: String): List<GeoLocationDto?>? {
        return geoApiClient.get("search") {
            parameter("country", country)
            parameter("name", city)
        }.body<GeoLocationResponse>().results
    }
}