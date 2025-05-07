import kotlinx.serialization.Serializable
import org.example.data.dto.DailyDto

@Serializable
data class WeatherDto(
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val daily: DailyDto?
)

