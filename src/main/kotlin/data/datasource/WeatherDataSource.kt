package org.example.data.datasource

import logic.model.LocationModel
import logic.model.WeatherModel

interface WeatherDataSource {
    suspend fun getWeather(): WeatherModel?
}