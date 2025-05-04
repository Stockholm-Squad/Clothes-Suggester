package org.example.data.datasource

import org.example.data.model.LocationModel

interface LocationDataSource {
    suspend fun getCurrentLocation(country: String,city: String): LocationModel
}