package org.example.logic.usecase

import logic.model.LocationModel
import org.example.logic.repository.WeatherRepository

class GetWeatherUseCase (
    private val weatherRepository: WeatherRepository
){
    suspend fun getWeather(locationModel: LocationModel): Double{
        val weather= weatherRepository.getWeather(locationModel)
        val temperature= weather.first()
        return temperature.maxTemp
    }
}