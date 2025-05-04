package org.example.logic.usecase

import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.logic.repository.WeatherRepository

class GetWeatherUseCase (
    private val weatherRepository: WeatherRepository,
){
    fun getWeather(locationModel: LocationModel): WeatherModel? {
        TODO("Not yet implemented")
    }
}