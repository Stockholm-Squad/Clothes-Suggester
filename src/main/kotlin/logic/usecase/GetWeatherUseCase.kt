package org.example.logic.usecase

import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.logic.exceptions.LoadingDataException
import org.example.logic.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend fun getWeatherOfDay(locationModel: LocationModel): WeatherModel {
        try {
            val weather = weatherRepository.getWeather(locationModel)
            return weather.first()
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LoadingDataException()
        }

    }
}