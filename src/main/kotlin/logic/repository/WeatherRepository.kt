package org.example.logic.repository

import logic.model.WeatherModel

interface WeatherRepository {
    suspend fun getWeather(): WeatherModel?
}