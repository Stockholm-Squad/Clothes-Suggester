package org.example.data.datasource

import WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import logic.model.LocationModel

class WeatherDataSourceImpl : WeatherDataSource {
    override suspend fun getWeather(locationModel: LocationModel): WeatherDto {
        val client = HttpClient(CIO)
        val url =
            "https://api.open-meteo.com/v1/forecast?latitude=${locationModel.lat}&" +
                    "longitude=${locationModel.lon}&daily=temperature_2m_max&" +
                    "daily=windspeed_10m_max&daily=temperature_2m_min"
        val response = client.get(url)
        val json = Json {
            ignoreUnknownKeys = true
        }
        return json.decodeFromString(response.body())
    }
}