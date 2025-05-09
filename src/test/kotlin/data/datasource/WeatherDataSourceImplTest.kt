package data.datasource


import WeatherDto
import com.google.common.truth.Truth.assertThat
import io.ktor.client.*
import kotlinx.coroutines.test.runTest
import logic.model.LocationModel
import org.example.data.datasource.weather.WeatherDataSourceImpl
import org.example.data.dto.DailyDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class WeatherDataSourceImplTest {
    private lateinit var client: HttpClient
    private lateinit var weatherDataSourceImpl: WeatherDataSourceImpl
    private lateinit var location: LocationModel

    @BeforeEach
    fun setUp() {
        location = LocationMocker().getFakeLocation()
    }

    @Test
    fun `getWeather() should return weatherDto when get data successfully `() = runTest {
        //Given
        val fakeJson = """
        { "latitude": "${location.latitude}",
                      "longitude": "${location.longitude}",
                      "timezone": "${location.timezone}",
                      "daily": {
                        "time": ["2025-05-06"],
                        "temperature_2m_max": [34.4],
                        "temperature_2m_min": [20.6],
                        "windspeed_10m_max": [23.0]
                      }
                    }
    """
        val weatherDto = WeatherDto(
            latitude = location.latitude,
            longitude = location.longitude,
            timezone = location.timezone,
            daily = DailyDto(
                time = listOf("2025-05-06"),
                temperatureMax = listOf(34.4),
                temperatureMin = listOf(20.6),
                windSpeed = listOf(23.0)
            )
        )

        client = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        weatherDataSourceImpl = WeatherDataSourceImpl(client)

        //When
        val result = weatherDataSourceImpl.getWeather(location)

        // Then
        assertThat(result).isEqualTo(weatherDto)


    }

    @Test
    fun `getWeather() should throw exception when get data fails `() = runTest {
        //Given
        val fakeJson = """
        { "lat": 26.6,
                      "longitude": 31.7,
                      "timezone": "Africa/Cairo",
    """
        client = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        weatherDataSourceImpl = WeatherDataSourceImpl(client)

        //When & Then
        assertThrows<Exception> {
            weatherDataSourceImpl.getWeather(location)
        }


    }
}