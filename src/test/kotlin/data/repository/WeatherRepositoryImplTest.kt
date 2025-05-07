package data.repository

import WeatherDto
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.data.datasource.WeatherDataSource
import org.example.data.dto.DailyDto
import org.example.data.repository.WeatherRepositoryImpl
import org.example.logic.exceptions.LoadingDataException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test


class WeatherRepositoryImplTest {
    private lateinit var weatherDataSource: WeatherDataSource
    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl

    @BeforeEach
    fun setUp() {
        weatherDataSource = mockk(relaxed = true)
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherDataSource)
    }
    @Test
    fun `getWeather() should return weather list when API succeeds`() = runTest {
        // Given
        val weatherDto = WeatherDto(
            latitude = 26.6,
            longitude = 31.7,
            timezone = "Africa/Cairo",
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
            weatherDataSource.getWeather(LocationModel(26.6, 31.7, "Africa/Cairo"))
        } returns weatherDto

        // When
        val result = weatherRepositoryImpl.getWeather(LocationModel(26.6, 31.7, "Africa/Cairo"))

        // Then
        assertThat(result).isEqualTo(expected)
    }
    @Test
    fun `getWeather() should throw exception when API fails`() = runTest {
        // Given
        coEvery {
            weatherDataSource.getWeather(LocationModel(26.6, 31.7, "Africa/Cairo"))
        } throws LoadingDataException()

        // When & Then
        assertThrows<LoadingDataException> {  weatherRepositoryImpl.getWeather(LocationModel(26.6, 31.7, "Africa/Cairo"))
        }
    }

}











