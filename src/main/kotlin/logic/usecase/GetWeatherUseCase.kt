package org.example.logic.usecase

import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.logic.repository.WeatherRepository

class GetWeatherUseCase (
    private val weatherRepository: WeatherRepository
){
    suspend fun getWeatherOfDay(locationModel: LocationModel): WeatherModel{
        val weather= weatherRepository.getWeather(locationModel)
        return weather.first()
    }
}