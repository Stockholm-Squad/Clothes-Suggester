package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import logic.model.LocationModel
import org.example.logic.exceptions.InvalidInput
import org.example.logic.exceptions.NoWeatherFoundException
import org.example.logic.repository.LocationRepository
import org.example.logic.usecase.GetLocationUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetLocationUseCaseTest {
    private lateinit var locationRepository: LocationRepository
    private lateinit var getLocationUseCase: GetLocationUseCase
    private lateinit var locationModel: LocationModel

    @BeforeEach
    fun setUp() {
        locationRepository = mockk(relaxed = true)
        getLocationUseCase = GetLocationUseCase(locationRepository)
        locationModel = LocationModel(
            latitude = 33.54,
            longitude = 32.65,
            city = "City",
            country = "Cairo",
            countryCode = "code",
            timezone = "timezone"
        )
    }

    @Test
    fun `getCurrentLocation() should return locationModel when repo returns data`() = runTest {
        //Given
        coEvery { locationRepository.getCurrentLocation() } returns locationModel

        //When
        val result = getLocationUseCase.getCurrentLocation()

        //Then
        assertThat(result).isEqualTo(locationModel)

    }

    @Test
    fun `getCurrentLocation() should throw Exception when repo throw exception`() = runTest {
        //Given
        coEvery { locationRepository.getCurrentLocation() } throws Exception()

        //When & Then
        assertThrows<Exception> { getLocationUseCase.getCurrentLocation() }
    }

    @Test
    fun `getLocationByCountryAndCity() should return locationModel when repo returns data`() = runTest {
        //Given
        val county = "Egypt"
        val city = "city"
        coEvery {
            locationRepository.getLocationByCountryAndCity(
                country = county,
                city = city
            )
        } returns locationModel

        //When
        val result = getLocationUseCase.getLocationByCountryAndCity(
            country = county,
            city = city
        )

        //Then
        assertThat(result).isEqualTo(locationModel)
    }

    @Test
    fun `getLocationByCountryAndCity() should throw NoWeatherFoundException when repo throw exception`() = runTest {
        //Given
        val county = "Egypt"
        val city = "city"
        coEvery {
            locationRepository.getLocationByCountryAndCity(
                country = county,
                city = city
            )
        } throws NoWeatherFoundException()

        //When & Then
        assertThrows<NoWeatherFoundException> {
            getLocationUseCase.getLocationByCountryAndCity(
                country = county,
                city = city
            )
        }
    }

    @Test
    fun `getLocationByCountryAndCity() should throw InvalidInputs when country is null`() = runTest {
        //Given
        val county = null
        val city = null

        //When & Then
        assertThrows<InvalidInput> {
            getLocationUseCase.getLocationByCountryAndCity(
                country = county,
                city = city
            )
        }
    }

    @Test
    fun `getLocationByCountryAndCity() should throw InvalidInputs when city is null`() = runTest {
        //Given
        val county = "Egypt"
        val city = null

        //When & Then
        assertThrows<InvalidInput> {
            getLocationUseCase.getLocationByCountryAndCity(
                country = county,
                city = city
            )
        }
    }
}