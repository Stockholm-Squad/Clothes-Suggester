package org.example.data.apiservice

import io.ktor.client.*

interface HttpClientProvider {
    fun createHttpClient(urlString: String): HttpClient
}