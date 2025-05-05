package org.example.data.repository

import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.data.datasource.WeatherDataSource
import org.example.data.mapper.toWeatherModelList
import org.example.logic.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource,
) : WeatherRepository {
    override suspend fun getWeather(locationModel: LocationModel): List<WeatherModel> {
        val weatherModel = weatherDataSource.getWeather(locationModel )
        return weatherModel.toWeatherModelList()
    }


}


