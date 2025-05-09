package data.repository

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.data.datasource.location.LocationDataSource
import org.example.data.dto.GeoLocationDto
import org.example.data.dto.IpLocationDto
import org.example.data.repository.LocationRepositoryImpl
import org.example.logic.exceptions.NoLocationFoundException
import org.example.logic.exceptions.NoWeatherFoundException
import org.example.logic.repository.LocationRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LocationRepositoryImplTest {

    private lateinit var locationRepository: LocationRepository
    private lateinit var locationDataSource: LocationDataSource

    @BeforeEach
    fun setUp() {
        locationDataSource = mockk(relaxed = true)
        locationRepository = LocationRepositoryImpl(locationDataSource)
    }

    @Test
    fun `getCurrentLocation() should return LocationModel when datasource success to get the data`() = runTest {
        //Given
        val ipLocationDto = IpLocationDto(
            latitude = 30.23,
            longitude = 31.89,
            city = "Cairo",
            country = "Egypt",
            countryCode = "Code",
            timezone = "timeZone"
        )
        coEvery { locationDataSource.getCurrentLocation() } returns ipLocationDto

        //When
        val result = locationRepository.getCurrentLocation()

        //Then
        assertThat(result?.city).isEqualTo(ipLocationDto.city)
    }

    @Test
    fun `getCurrentLocation() should throw NoLocationFoundException when datasource failed to get the data`() =
        runTest {
            //Given
            coEvery { locationDataSource.getCurrentLocation() } throws NoLocationFoundException()

            //When & Then
            assertThrows<NoLocationFoundException> { locationRepository.getCurrentLocation() }
        }


    @Test
    fun `getLocationByCountryAndCity() should return LocationModel when datasource success to get the data`() =
        runTest {
            //Given
            val country = "Egypt"
            val city = "Cairo"
            val geoLocationDtoList = listOf(
                GeoLocationDto(
                    latitude = 30.23,
                    longitude = 31.89,
                    city = "Cairo",
                    country = "Egypt",
                    countryCode = "Code",
                    timezone = "timeZone"
                )
            )
            coEvery {
                locationDataSource.getLocationByCountryAndCity(
                    country = country,
                    city = city
                )
            } returns geoLocationDtoList

            //When
            val result = locationRepository.getLocationByCountryAndCity(country = country, city = city)

            //Then
            assertThat(result?.city).isEqualTo(city)
        }

    @Test
    fun `getLocationByCountryAndCity() should throw NoWeatherFoundException when datasource failed to get the data`() =
        runTest {
            //Given
            val country = "Egypt"
            val city = "Cairo"
            coEvery {
                locationDataSource.getLocationByCountryAndCity(
                    country = country,
                    city = city
                )
            } throws NoWeatherFoundException()

            //When & Then
            assertThrows<NoWeatherFoundException> {
                locationRepository.getLocationByCountryAndCity(
                    country = country,
                    city = city
                )
            }
        }

    @Test
    fun `getLocationByCountryAndCity() should return null when datasource returns null`() =
        runTest {
            //Given
            val country = "Egypt"
            val city = "Cairo"
            coEvery {
                locationDataSource.getLocationByCountryAndCity(
                    country = country,
                    city = city
                )
            } returns null

            //When
            val result = locationRepository.getLocationByCountryAndCity(
                country = country,
                city = city
            )

            // Then
            assertThat(result).isEqualTo(null)
        }
}