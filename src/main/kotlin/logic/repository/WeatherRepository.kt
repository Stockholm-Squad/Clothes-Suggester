package org.example.logic.repository

import logic.model.LocationModel
import logic.model.WeatherModel

interface WeatherRepository {
    suspend fun getWeather(locationModel: LocationModel?): WeatherModel?
}