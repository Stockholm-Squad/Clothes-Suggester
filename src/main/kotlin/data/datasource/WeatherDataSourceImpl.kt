package org.example.data.datasource

import WeatherDto
import io.ktor.client.call.*
import io.ktor.client.request.*
import logic.model.LocationModel
import org.example.data.apiservice.HttpClientProvider

class WeatherDataSourceImpl(
    private val httpClientProvider: HttpClientProvider,
    private val weatherLink: String
) : WeatherDataSource {
    override suspend fun getWeather(locationModel: LocationModel): WeatherDto {
        return httpClientProvider.createHttpClient(weatherLink).get("forecast") {
            parameter("latitude", locationModel.latitude)
            parameter("longitude", locationModel.longitude)
            parameter("daily", "temperature_2m_max")
            parameter("daily", "windspeed_10m_max")
            parameter("daily", "temperature_2m_min")
        }.body<WeatherDto>()

    }
}