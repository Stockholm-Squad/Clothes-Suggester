package data.datasource

import com.google.common.truth.Truth.assertThat
import io.ktor.client.*
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.data.datasource.LocationDataSource
import org.example.data.datasource.LocationDataSourceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LocationDataSourceImplTest {
    private lateinit var ipApiClient: HttpClient
    private lateinit var geoApiClient: HttpClient
    private lateinit var locationDataSourceImpl: LocationDataSource

    @BeforeEach
    fun setUp() {
        ipApiClient = mockk()
        geoApiClient = mockk()
        locationDataSourceImpl = LocationDataSourceImpl(ipApiClient, geoApiClient)
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

        ipApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        locationDataSourceImpl = LocationDataSourceImpl(ipApiClient = ipApiClient, geoApiClient = geoApiClient)

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
        ipApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        locationDataSourceImpl = LocationDataSourceImpl(ipApiClient = ipApiClient, geoApiClient = geoApiClient)

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

        geoApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
        locationDataSourceImpl = LocationDataSourceImpl(ipApiClient = ipApiClient, geoApiClient = geoApiClient)

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
            geoApiClient = HttpClientMocker().mockHttpClientWithResponse(fakeJson)
            locationDataSourceImpl = LocationDataSourceImpl(ipApiClient = ipApiClient, geoApiClient = geoApiClient)

            //When & Then
            assertThrows<Exception> {
                locationDataSourceImpl.getLocationByCountryAndCity(country = country, city = city)
            }
        }
}