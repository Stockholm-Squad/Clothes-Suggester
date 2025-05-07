package org.example.data.repository

import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.data.datasource.WeatherDataSource
import org.example.data.mapper.toWeatherModelList
import org.example.logic.exceptions.NoWeatherFoundException
import org.example.logic.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource,
) : WeatherRepository, BaseRepository() {
    override suspend fun getWeather(locationModel: LocationModel): List<WeatherModel> = tryCatch(
        onSuccess = { weatherDataSource.getWeather(locationModel).toWeatherModelList() },
        onFailure = { throw NoWeatherFoundException() }
    )
}


