package org.example.data.datasource

import logic.model.LocationModel

class LocationDataSourceImpl: LocationDataSource {

    override suspend fun getCurrentLocation(): LocationModel? {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationByCountry(country: String): LocationModel? {
        TODO("Not yet implemented")
    }
}