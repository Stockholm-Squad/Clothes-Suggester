package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import logic.model.LocationModel
import logic.model.WeatherModel
import org.example.logic.repository.WeatherRepository
import org.example.logic.usecase.GetWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test


class GetWeatherUseCaseTest {
    private lateinit var getWeatherUseCase: GetWeatherUseCase
    private lateinit var weatherRepository: WeatherRepository


    @BeforeEach
    fun setUp() {
        weatherRepository = mockk(relaxed = true)
        getWeatherUseCase = GetWeatherUseCase(weatherRepository)
    }

    @Test
    fun `getWeatherOfDay() should return Temperature correctly when repo succeeds`() = runBlocking {

        // Given
        val excepted=30.0
        val weatherModel = listOf(
            WeatherModel("2025-05-05", 30.0, 20.0, 10.0)
        )


        coEvery { weatherRepository.getWeather(LocationModel(26.6, 31.7, "Africa/Cairo")) } returns weatherModel

        // When
        val result = getWeatherUseCase.getWeatherOfDay(LocationModel(26.6, 31.7, "Africa/Cairo"))

        // Then
        assertThat(result).isEqualTo(excepted)
    }

}
