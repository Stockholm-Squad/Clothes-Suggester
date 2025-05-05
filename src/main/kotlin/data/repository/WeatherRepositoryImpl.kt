package org.example.data.repository

import logic.model.WeatherModel
import org.example.data.datasource.WeatherDataSource
import org.example.logic.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource,
) : WeatherRepository {
    override suspend fun getWeather(): WeatherModel? {
        return weatherDataSource.getWeather()
    }
}