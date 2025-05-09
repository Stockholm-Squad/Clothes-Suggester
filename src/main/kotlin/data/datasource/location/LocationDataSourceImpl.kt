package org.example.data.datasource.location

import io.ktor.client.call.*
import io.ktor.client.request.*
import org.example.data.apiservice.HttpClientProvider
import org.example.data.dto.GeoLocationDto
import org.example.data.dto.GeoLocationResponseDto
import org.example.data.dto.IpLocationDto

class LocationDataSourceImpl(
    private val httpClientProvider: HttpClientProvider,
    private val ipLocationLink: String,
    private val geoLocationLink: String
) : LocationDataSource {

    override suspend fun getCurrentLocation(): IpLocationDto? {
        return httpClientProvider.createHttpClient(ipLocationLink)
            .get("json").body<IpLocationDto?>()
    }

    override suspend fun getLocationByCountryAndCity(country: String, city: String): List<GeoLocationDto?>? {
        return httpClientProvider.createHttpClient(geoLocationLink)
            .get("search") {
                parameter("country", country)
                parameter("name", city)
            }.body<GeoLocationResponseDto?>()?.results
    }
}