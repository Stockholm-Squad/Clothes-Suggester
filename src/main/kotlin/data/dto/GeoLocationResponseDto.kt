package org.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoLocationResponseDto(
    @SerialName("results") val results: List<GeoLocationDto?>?,
    @SerialName("generationtime_ms") val generationTime: Double?
)
