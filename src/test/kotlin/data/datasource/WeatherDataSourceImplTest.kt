package data.datasource

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import org.example.data.datasource.WeatherDataSourceImpl
import org.junit.jupiter.api.BeforeEach


class WeatherDataSourceImplTest {
    private lateinit var weatherDataSourceImpl: WeatherDataSourceImpl

    @BeforeEach
    fun setUp() {
        val mockEngine = MockEngine { request ->
            respond(
                content = """
                    {
                      "latitude": 26.6,
                      "longitude": 31.7,
                      "timezone": "Africa/Cairo",
                      "daily": {
                        "time": ["2025-05-06"],
                        "temperature_2m_max": [34.4],
                        "temperature_2m_min": [20.6],
                        "windspeed_10m_max": [23.0]
                      }
                    }
                """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

    }}