package org.example.data.repository

import logic.model.LocationModel
import org.example.data.datasource.LocationDataSource
import org.example.data.mapper.LocationMapper
import org.example.logic.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource,
    private val locationMapper: LocationMapper
) : LocationRepository {
    override suspend fun getCurrentLocation(): LocationModel? {
        return locationMapper.mapLocationDtoToEntity(
            locationDataSource.getCurrentLocation()
        )
    }

    override suspend fun getLocationByCountryAndCity(country: String, city: String): LocationModel? {
        return locationMapper.mapLocationDtoToEntity(
            locationDataSource.getLocationByCountryAndCity(country, city)
        )
    }
}