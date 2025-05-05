package org.example.data.repository

import logic.model.LocationModel
import org.example.data.datasource.LocationDataSource
import org.example.logic.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource
): LocationRepository {
    override suspend fun getCurrentLocation(): LocationModel? {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationByCountry(country: String): LocationModel? {
        TODO("Not yet implemented")
    }
}