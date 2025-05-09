package data.datasource


import WeatherDto
import com.google.common.truth.Truth.assertThat
import io.ktor.client.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import logic.model.LocationModel
import org.example.data.apiservice.HttpClientProvider
import org.example.data.datasource.WeatherDataSource
import org.example.data.datasource.WeatherDataSourceImpl
import org.example.data.dto.DailyDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class WeatherDataSourceImplTest {
    private lateinit var weatherDataSource: WeatherDataSource
    private lateinit var httpClientProvider: HttpClientProvider
    private lateinit var location: LocationModel
    private lateinit var weatherLink: String

    @BeforeEach
    fun setUp() {
        httpClientProvider = mockk(relaxed = true)
        location = LocationMocker().getFakeLocation()
        weatherLink = ""
        weatherDataSource = WeatherDataSourceImpl(httpClientProvider, weatherLink)
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

        val weatherApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        coEvery { httpClientProvider.createHttpClient(weatherLink) } returns weatherApiClient

        //When
        val result = weatherDataSource.getWeather(location)

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
        val weatherApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        coEvery { httpClientProvider.createHttpClient(weatherLink) } returns weatherApiClient

        //When & Then
        assertThrows<Exception> {
            weatherDataSource.getWeather(location)
        }


    }
}