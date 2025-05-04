package org.example.data.datasource

import org.example.data.model.WeatherModel
import org.example.data.model.WeatherRequest

interface WeatherDataSource {
    suspend fun getWeather(weatherRequest: WeatherRequest): WeatherModel?
}