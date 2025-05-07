package org.example.data.mapper

import WeatherDto
import logic.model.WeatherModel

fun WeatherDto?.toWeatherModelList(): List<WeatherModel> {
    if (this == null) return emptyList()
    val times = daily?.time
    val maxTemps = daily?.temperatureMax
    val minTemps = daily?.temperatureMin
    val windSpeeds = daily?.windSpeed

    if (times.isNullOrEmpty() || maxTemps.isNullOrEmpty() || minTemps.isNullOrEmpty() || windSpeeds.isNullOrEmpty()) {
        return emptyList()
    }

    return times.indices.map { index ->
        WeatherModel(
            date = times.getOrElse(index) { "" },
            maxTemp = maxTemps.getOrElse(index) { 0.0 },
            minTemp = minTemps.getOrElse(index) { 0.0 },
            maxWindSpeed = windSpeeds.getOrElse(index) { 0.0 }
        )
    }
}
