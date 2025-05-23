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

    suspend fun getLocationByCountryAndCity(country: String?, city: String?): LocationModel? {
        if (country.isNullOrBlank() || city.isNullOrBlank()) throw InvalidInput()
        return locationRepository.getLocationByCountryAndCity(country, city)
    }
}