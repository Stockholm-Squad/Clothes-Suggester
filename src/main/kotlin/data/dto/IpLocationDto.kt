package org.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpLocationDto(
    @SerialName("lat") val latitude: Double?,
    @SerialName("lon") val longitude: Double?,
    @SerialName("city") val city: String?,
    @SerialName("country") val country: String?,
    @SerialName("countryCode") val countryCode: String?,
    @SerialName("timezone") val timezone: String?
)
