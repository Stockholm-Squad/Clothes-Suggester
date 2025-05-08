package data.datasource

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.data.apiservice.HttpClientProvider
import org.example.data.datasource.LocationDataSource
import org.example.data.datasource.LocationDataSourceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LocationDataSourceImplTest {
    private lateinit var locationDataSourceImpl: LocationDataSource
    private lateinit var httpClientProvider: HttpClientProvider
    private lateinit var ipLocationLink: String
    private lateinit var geoLocationLink: String

    @BeforeEach
    fun setUp() {
        httpClientProvider = mockk(relaxed = true)
        ipLocationLink = ""
        geoLocationLink = ""
        locationDataSourceImpl = LocationDataSourceImpl(httpClientProvider, ipLocationLink, geoLocationLink)
    }

    @Test
    fun `getCurrentLocation() should return IpLocationDto when get data successfully `() = runTest {
        //Given
        val fakeJson = """
        {
            "lat": 40.0,
            "lon": -75.0,
            "city": "Test City",
            "country": "Test Country",
            "countryCode": "Region",
            "zip": "00000",
            "timezone": "UTC"
        }
    """
        val ipApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        coEvery { httpClientProvider.createHttpClient(ipLocationLink) } returns ipApiClient

        //When
        val result = locationDataSourceImpl.getCurrentLocation()

        //Then
        assertThat(result?.country).isEqualTo("Test Country")
        assertThat(result?.city).isEqualTo("Test City")

    }

    @Test
    fun `getCurrentLocation() should throw Exception when error happens while getting data `() = runTest {
        //Given
        val fakeJson = """
        {
            "lat": 40.0,
            "lon": -75.0,
            "city": "Test City",
            "countrytt": "Test Country",
            "countryCode": "Region",
            "zip": "00000",
            "timezone": "UTC"
        }
    """
        val ipApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        coEvery { httpClientProvider.createHttpClient(ipLocationLink) } returns ipApiClient

        //When & Then
        assertThrows<Exception> { locationDataSourceImpl.getCurrentLocation() }
    }


    @Test
    fun `getLocationByCountryAndCity() should return List of GeoLocationDto when get data successfully `() = runTest {
        //Given
        val country = "Egypt"
        val city = "Cairo"

        val fakeJson = """
        {   "results": [
                {
                    "latitude": 40.0,
                    "longitude": -75.0,
                    "name": "Test City",
                    "country": "Test Country",
                    "country_code": "Region",
                    "zip": "00000",
                    "timezone": "UTC"
                }
            ]
            "generationtime_ms": 898
        }
    """

        val geoApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        coEvery { httpClientProvider.createHttpClient(geoLocationLink) } returns geoApiClient

        //When
        val result = locationDataSourceImpl.getLocationByCountryAndCity(country = country, city = city)

        //Then
        assertThat(result?.get(0)?.country).isEqualTo("Test Country")
        assertThat(result?.get(0)?.city).isEqualTo("Test City")

    }

    @Test
    fun `getLocationByCountryAndCity() should throw Exception when error happens while getting data `() =
        runTest {
            //Given
            val country = "Egypt"
            val city = "Cairo"

            val fakeJson = """
            {
                "latitude": 40.0,
                "longitude": -75.0,
                "name": "Test City",
                "country": "Test Country",
                "country_code": "Region",
                "zip": "00000",
                "timezone": "UTC"
            }
        """
            val geoApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
            coEvery { httpClientProvider.createHttpClient(geoLocationLink) } returns geoApiClient

            //When & Then
            assertThrows<Exception> {
                locationDataSourceImpl.getLocationByCountryAndCity(country = country, city = city)
            }
        }
}