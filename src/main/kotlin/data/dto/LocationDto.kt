package org.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("query") val ip: String?,
    @SerialName("lat") val latitude: Double?,
    @SerialName("lon") val longitude: Double?,
    val city: String?,
    val country: String?,
    val regionName: String?,
    val zip: String?,
    val timezone: String?
)
