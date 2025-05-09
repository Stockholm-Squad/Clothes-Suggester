package org.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    @SerialName("time") val time: List<String>?,
    @SerialName("temperature_2m_max") val temperatureMax: List<Double>?,
    @SerialName("temperature_2m_min") val temperatureMin: List<Double>?,
    @SerialName("windspeed_10m_max") val windSpeed: List<Double>?
)