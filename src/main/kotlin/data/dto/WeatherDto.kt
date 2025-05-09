import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.data.dto.DailyDto

@Serializable
data class WeatherDto(
    @SerialName("latitude") val latitude: Double?,
    @SerialName("longitude") val longitude: Double?,
    @SerialName("timezone") val timezone: String?,
    @SerialName("daily") val daily: DailyDto?
)

