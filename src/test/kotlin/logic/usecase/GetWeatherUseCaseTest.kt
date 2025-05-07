package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.datasource.LocationMocker
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.logic.exceptions.LoadingDataException
import org.example.logic.repository.WeatherRepository
import org.example.logic.usecase.GetWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test


class GetWeatherUseCaseTest {
    private lateinit var getWeatherUseCase: GetWeatherUseCase
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var location: LocationModel


    @BeforeEach
    fun setUp() {
        weatherRepository = mockk(relaxed = true)
        getWeatherUseCase = GetWeatherUseCase(weatherRepository)
        location = LocationMocker().getFakeLocation()
    }

    @Test
    fun `getWeatherOfDay() should return weather correctly when repo succeeds`() = runTest {
        // Given
        val exceptedWeather = WeatherModel(date = "2025-05-06", maxTemp = 34.4, minTemp = 20.6, maxWindSpeed = 23.0)
        val weatherModel = listOf(
            WeatherModel(date = "2025-05-06", maxTemp = 34.4, minTemp = 20.6, maxWindSpeed = 23.0)
        )
        coEvery { weatherRepository.getWeather(locationModel = location) } returns weatherModel

        // When
        val result = getWeatherUseCase.getWeatherOfDay(location)

        // Then
        assertThat(result).isEqualTo(exceptedWeather)
    }

    @Test
    fun `getWeatherOfDay() should throw exception when repo fails`() = runTest {
        // Given
        coEvery { weatherRepository.getWeather(location) } throws LoadingDataException()

        // When & Then
        assertThrows<LoadingDataException> {
            getWeatherUseCase.getWeatherOfDay(location)
        }
    }

}
