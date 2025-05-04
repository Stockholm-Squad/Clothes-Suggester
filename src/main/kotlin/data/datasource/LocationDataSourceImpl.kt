package org.example.data.datasource

import org.example.data.model.LocationModel

class LocationDataSourceImpl: LocationDataSource {
    override suspend fun getCurrentLocation(
        country: String,
        city: String
    ): LocationModel {
        TODO("Not yet implemented")
    }
}