package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.example.logic.repository.LocationRepository
import org.example.logic.repository.WeatherRepository
import org.example.logic.usecase.GetWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test


class GetWeatherUseCaseTest {
    private lateinit var getWeatherUseCase: GetWeatherUseCase
    private lateinit var locationRepository: LocationRepository
    private lateinit var weatherRepository: WeatherRepository


    @BeforeEach
    fun setUp() {
        locationRepository=mockk(relaxed = true)
        weatherRepository=mockk(relaxed = true)
        getWeatherUseCase= GetWeatherUseCase(locationRepository,weatherRepository)
    }
    @Test
    fun `getWeather() should return true when repo succeeds`(){
        // Given
            runBlocking {
                coEvery { weatherRepository.getWeather()}

        // When
             val result =   getWeatherUseCase.getWeather()

        // Then
     assertThat(result)

    }}
}