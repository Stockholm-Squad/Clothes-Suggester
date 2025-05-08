package org.example.data.datasource

import WeatherDto
import logic.model.LocationModel

interface WeatherDataSource {
    suspend fun getWeather(locationModel: LocationModel): WeatherDto?
}