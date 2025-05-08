package org.example.data.mapper

import WeatherDto
import logic.model.WeatherModel

fun WeatherDto?.toWeatherModelList(): List<WeatherModel?>? {
    if (this == null) return null
    val times = daily?.time
    val maxTemps = daily?.temperatureMax
    val minTemps = daily?.temperatureMin
    val windSpeeds = daily?.windSpeed

    if (times.isNullOrEmpty() ||
        maxTemps.isNullOrEmpty() ||
        minTemps.isNullOrEmpty() ||
        windSpeeds.isNullOrEmpty()
    ) {
        return null
    }

    return times.indices.map { index ->
        WeatherModel(
            date = times.getOrNull(index),
            maxTemp = maxTemps.getOrNull(index),
            minTemp = minTemps.getOrNull(index),
            maxWindSpeed = windSpeeds.getOrNull(index)
        )
    }
}
