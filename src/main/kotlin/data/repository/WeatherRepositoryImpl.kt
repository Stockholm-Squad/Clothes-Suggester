package org.example.data.repository

import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.data.datasource.WeatherDataSource
import org.example.logic.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource,
) : WeatherRepository {
    override suspend fun getWeather(locationModel: LocationModel?): WeatherModel? {
        return weatherDataSource.getWeather(locationModel)
    }
}