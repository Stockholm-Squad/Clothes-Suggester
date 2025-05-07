package logic.model

data class WeatherModel(
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,
    val maxWindSpeed: Double
)