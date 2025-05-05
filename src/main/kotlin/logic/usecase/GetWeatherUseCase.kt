package org.example.logic.usecase

import org.example.logic.repository.LocationRepository
import org.example.logic.repository.WeatherRepository

class GetWeatherUseCase (
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
){
    suspend fun getWeather(): Double ?{
        val location=locationRepository.getCurrentLocation()
        val weather= weatherRepository.getWeather(location)
        val temperature=weather?.daily?.temperatureMax
        return temperature?.first()
    }
}