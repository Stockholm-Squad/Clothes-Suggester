package org.example.data.mapper

import logic.model.LocationModel
import org.example.data.dto.LocationDto

class LocationMapper {
    fun mapLocationDtoToEntity(locationDto: LocationDto?): LocationModel? {
        if (locationDto == null) return null
        return LocationModel(
            ip = locationDto.ip,
            latitude = locationDto.latitude,
            longitude = locationDto.longitude,
            city = locationDto.city,
            country = locationDto.country,
            regionName = locationDto.regionName,
            zip = locationDto.zip,
            timezone = locationDto.timezone
        )
    }

    fun mapLocationEntityToDto(locationModel: LocationModel?): LocationDto? {
        if (locationModel == null) return null
        return LocationDto(
            ip = locationModel.ip,
            latitude = locationModel.latitude,
            longitude = locationModel.longitude,
            city = locationModel.city,
            country = locationModel.country,
            regionName = locationModel.regionName,
            zip = locationModel.zip,
            timezone = locationModel.timezone
        )
    }
}