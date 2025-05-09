package data.repository

import WeatherDto
import com.google.common.truth.Truth.assertThat
import data.datasource.LocationMocker
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.data.datasource.weather.WeatherDataSource
import org.example.data.dto.DailyDto
import org.example.data.repository.WeatherRepositoryImpl
import org.example.logic.exceptions.NoWeatherFoundException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test


class WeatherRepositoryImplTest {
    private lateinit var weatherDataSource: WeatherDataSource
    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl
    private lateinit var location: LocationModel

    @BeforeEach
    fun setUp() {
        weatherDataSource = mockk(relaxed = true)
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherDataSource)
        location = LocationMocker().getFakeLocation()
    }

    @Test
    fun `getWeather() should return weather list when API succeeds`() = runTest {
        // Given
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

        val expected = listOf(
            WeatherModel(
                date = "2025-05-06",
                maxTemp = 34.4,
                minTemp = 20.6,
                maxWindSpeed = 23.0
            )
        )

        coEvery {
            weatherDataSource.getWeather(location)
        } returns weatherDto

        // When
        val result = weatherRepositoryImpl.getWeather(location)

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `getWeather() should throw exception when API fails`() = runTest {
        // Given
        coEvery {
            weatherDataSource.getWeather(location)
        } throws NoWeatherFoundException()

        // When & Then
        assertThrows<NoWeatherFoundException> {
            weatherRepositoryImpl.getWeather(location)
        }
    }

}











