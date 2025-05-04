package org.example.data.datasource

import org.example.data.model.WeatherModel
import org.example.data.model.WeatherRequest

class WeatherDataSourceImpl: WeatherDataSource {
    override suspend fun getWeather(weatherRequest: WeatherRequest): WeatherModel? {
        TODO("Not yet implemented")
    }
}