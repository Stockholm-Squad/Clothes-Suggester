package org.example.data.repository

import logic.model.LocationModel
import org.example.data.datasource.LocationDataSource
import org.example.data.mapper.toModel
import org.example.logic.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource
) : LocationRepository {
    override suspend fun getCurrentLocation(): LocationModel? {
        return locationDataSource.getCurrentLocation().toModel()
    }

    override suspend fun getLocationByCountryAndCity(country: String, city: String): LocationModel? {
        return locationDataSource.getLocationByCountryAndCity(country, city).toModel()

    }
}