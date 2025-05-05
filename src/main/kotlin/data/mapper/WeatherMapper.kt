package org.example.data.mapper

import WeatherDto
import logic.model.WeatherModel

fun WeatherDto.toWeatherModelList(): List<WeatherModel>{
    return  daily.time.indices.map { index->
        WeatherModel(
            date =daily.time[index],
            maxTemp = daily.temperatureMax.getOrElse(index){0.0},
            minTemp = daily.temperatureMin.getOrElse(index){0.0},
            maxWindSpeed = daily.windSpeed.getOrElse(index){0.0}
        )

    }
}