package org.example.data.apiservice

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class HttpClientProviderImp: HttpClientProvider {
    override fun createHttpClient(urlString: String): HttpClient {
        return HttpClient(CIO)
        {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            defaultRequest {
                url(urlString)
            }

        }
    }
}