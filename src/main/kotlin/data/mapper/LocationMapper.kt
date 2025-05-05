package org.example.data.mapper

import logic.model.LocationModel
import org.example.data.dto.GeoLocationDto
import org.example.data.dto.IpLocationDto

fun IpLocationDto?.toModel(): LocationModel? {
    if (this == null) return null
    return LocationModel(
        latitude = this.latitude,
        longitude = this.longitude,
        city = this.city,
        country = this.country,
        timezone = this.timezone,
        countryCode = this.countryCode,
    )
}

fun GeoLocationDto?.toModel(): LocationModel? {
    if (this == null) return null
    return LocationModel(
        latitude = this.latitude,
        longitude = this.longitude,
        city = this.city,
        country = this.country,
        timezone = this.timezone,
        countryCode = this.countryCode,
    )
}

