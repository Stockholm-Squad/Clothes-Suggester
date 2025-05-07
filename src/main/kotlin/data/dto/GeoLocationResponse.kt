package org.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GeoLocationResponse(
    val results: List<GeoLocationDto?>?,
    val generationtime_ms: Double?
)
