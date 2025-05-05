package org.example.logic.usecase

import logic.model.LocationModel
import org.example.logic.exceptions.InvalidInput
import org.example.logic.repository.LocationRepository

class GetLocationUseCase(
    private val locationRepository: LocationRepository,
) {
    suspend fun getCurrentLocation(): LocationModel? {
        return locationRepository.getCurrentLocation()
    }

    suspend fun getLocationByCountry(country: String?, city: String?): LocationModel? {
        if (country == null || city == null) throw InvalidInput()
        return locationRepository.getLocationByCountry(country, city)
    }
}