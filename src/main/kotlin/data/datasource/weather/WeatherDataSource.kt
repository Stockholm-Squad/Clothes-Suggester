package org.example.data.datasource.weather

import WeatherDto
import logic.model.LocationModel

interface WeatherDataSource {
    suspend fun getWeather(locationModel: LocationModel): WeatherDto
}