package data.datasource

import logic.model.LocationModel

class LocationMocker {
    fun getFakeLocation(
        latitude: Double = 26.55,
        longitude: Double = 31.66,
        country: String = "Egypt",
        city: String = "Cairo"
    ): LocationModel {
        return LocationModel(
            latitude, longitude, city,
            country = country,
            countryCode = "",
            timezone = "cairo/egypt"
        )
    }
}