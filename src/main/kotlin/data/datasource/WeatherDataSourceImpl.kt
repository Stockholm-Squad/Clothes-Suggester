package org.example.data.datasource

import logic.model.LocationModel
import logic.model.WeatherModel

class WeatherDataSourceImpl: WeatherDataSource {
    override suspend fun getWeather(locationModel: LocationModel): WeatherModel? {
        TODO("Not yet implemented")
    }
}