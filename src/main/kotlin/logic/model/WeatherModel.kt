package logic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherModel(
    val latitude: Double,
    val longitude: Double,
    @SerialName("generationtime_ms") val generationMs: Double,
    @SerialName("utc_offset_seconds")val utcOffsetSeconds: Int,
    val timezone: String,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @SerialName("daily_units") val dailyUnits: DailyUnits,
    val daily: DailyData
)

@Serializable
data class DailyUnits(
    val time: String,
    @SerialName("temperature_2m_max") val temperatureMaxUnit: String,
    @SerialName("windspeed_10m_max")   val windSpeed: String,
    @SerialName("temperature_2m_min")val temperatureMinUnit: String
)

@Serializable
data class DailyData(
    val time: List<String>,
    @SerialName("temperature_2m_max") val temperatureMax: List<Double>,
    @SerialName("windspeed_10m_max") val windSpeedMax: List<Double>,
    @SerialName("temperature_2m_min") val temperatureMin: List<Double>
)

