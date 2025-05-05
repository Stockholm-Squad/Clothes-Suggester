package org.example.logic.usecase

import logic.model.LocationModel
import org.example.logic.repository.LocationRepository

class GetLocationUseCase(
    private val locationRepository: LocationRepository,
) {
    fun getCurrentLocation(): LocationModel? {
        TODO("Not yet implemented")
    }
    fun getLocationByCountry(country: String): LocationModel? {
        TODO("Not yet implemented")
    }
}