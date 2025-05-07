package org.example.data.repository

import logic.model.LocationModel
import org.example.data.datasource.LocationDataSource
import org.example.data.mapper.toModel
import org.example.logic.exceptions.NoLocationFoundException
import org.example.logic.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource
) : LocationRepository, BaseRepository() {
    override suspend fun getCurrentLocation(): LocationModel? = super.tryCatch(
        onSuccess = { locationDataSource.getCurrentLocation().toModel() },
        onFailure = { throw NoLocationFoundException() }
    )

    override suspend fun getLocationByCountryAndCity(country: String, city: String): LocationModel? = super.tryCatch(
        onSuccess = { locationDataSource.getLocationByCountryAndCity(country, city)?.firstOrNull().toModel() },
        onFailure = { throw NoLocationFoundException() }
    )
}