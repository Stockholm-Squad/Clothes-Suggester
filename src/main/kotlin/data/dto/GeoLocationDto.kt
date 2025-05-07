package org.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoLocationDto(
    @SerialName("name") val city: String?,
    @SerialName("latitude") val latitude: Double?,
    @SerialName("longitude") val longitude: Double?,
    @SerialName("country") val country: String?,
    @SerialName("timezone") val timezone: String?,
    @SerialName("country_code") val countryCode: String?,
)